package cn.forbearance.mall.web;

import com.alibaba.fastjson.JSONObject;
import cn.forbearance.mall.util.AdminResponseCode;
import cn.forbearance.mall.util.AdminResponseUtil;
import cn.forbearance.mall.util.Permission;
import cn.forbearance.mall.util.PermissionUtil;
import cn.forbearance.mall.util.JacksonUtil;
import cn.forbearance.mall.util.ResponseUtil;
import cn.forbearance.mall.pojo.DtsAdmin;
import cn.forbearance.mall.service.DtsPermissionService;
import cn.forbearance.mall.service.DtsRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
	private static final Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

	@Autowired
	private DtsRoleService roleService;
	@Autowired
	private DtsPermissionService permissionService;

	/*
	 * { username : value, password : value }
	 */
	@PostMapping("/login")
	public Object login(@RequestBody String body) {
		logger.info("【请求开始】系统管理->用户登录,请求参数:body:{}", body);

		String username = JacksonUtil.parseString(body, "username");
		String password = JacksonUtil.parseString(body, "password");

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return ResponseUtil.badArgument();
		}

		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(new UsernamePasswordToken(username, password));
		} catch (UnknownAccountException uae) {
			logger.error("系统管理->用户登录  错误:{}", AdminResponseCode.ADMIN_INVALID_ACCOUNT_OR_PASSWORD.desc());
			return AdminResponseUtil.fail(AdminResponseCode.ADMIN_INVALID_ACCOUNT_OR_PASSWORD);
		} catch (LockedAccountException lae) {
			logger.error("系统管理->用户登录 错误:{}", AdminResponseCode.ADMIN_LOCK_ACCOUNT.desc());
			return AdminResponseUtil.fail(AdminResponseCode.ADMIN_LOCK_ACCOUNT);

		} catch (AuthenticationException ae) {
			logger.error("系统管理->用户登录 错误:{}", AdminResponseCode.ADMIN_LOCK_ACCOUNT.desc());
			return AdminResponseUtil.fail(AdminResponseCode.ADMIN_INVALID_AUTH);
		}

		logger.info("【请求结束】系统管理->用户登录,响应结果:{}", JSONObject.toJSONString(currentUser.getSession().getId()));
		return ResponseUtil.ok(currentUser.getSession().getId());
	}

	/*
	 *
	 */
	@RequiresAuthentication
	@PostMapping("/logout")
	public Object login() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();

		logger.info("【请求结束】系统管理->用户注销,响应结果:{}", JSONObject.toJSONString(currentUser.getSession().getId()));
		return ResponseUtil.ok();
	}

	@RequiresAuthentication
	@GetMapping("/info")
	public Object info() {
		Subject currentUser = SecurityUtils.getSubject();
		DtsAdmin admin = (DtsAdmin) currentUser.getPrincipal();

		Map<String, Object> data = new HashMap<>();
		data.put("name", admin.getUsername());
		data.put("avatar", admin.getAvatar());

		Integer[] roleIds = admin.getRoleIds();
		Set<String> roles = roleService.queryByIds(roleIds);
		Set<String> permissions = permissionService.queryByRoleIds(roleIds);
		data.put("roles", roles);
		// NOTE
		// 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
		data.put("perms", toAPI(permissions));

		logger.info("【请求结束】系统管理->用户信息获取,响应结果:{}", JSONObject.toJSONString(data));
		return ResponseUtil.ok(data);
	}

	@Autowired
	private ApplicationContext context;
	private HashMap<String, String> systemPermissionsMap = null;

	private Collection<String> toAPI(Set<String> permissions) {
		if (systemPermissionsMap == null) {
			systemPermissionsMap = new HashMap<>();
			final String basicPackage = "cn.forbearance.mall";
			List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
			for (Permission permission : systemPermissions) {
				String perm = permission.getRequiresPermissions().value()[0];
				String api = permission.getApi();
				systemPermissionsMap.put(perm, api);
			}
		}

		Collection<String> apis = new HashSet<>();
		for (String perm : permissions) {
			String api = systemPermissionsMap.get(perm);
			apis.add(api);

			if (perm.equals("*")) {
				apis.clear();
				apis.add("*");
				return apis;
				// return systemPermissionsMap.values();

			}
		}
		return apis;
	}

	@GetMapping("/401")
	public Object page401() {
		return ResponseUtil.unlogin();
	}

	@GetMapping("/index")
	public Object pageIndex() {
		return ResponseUtil.ok();
	}

	@GetMapping("/403")
	public Object page403() {
		return ResponseUtil.unauthz();
	}
}
