package cn.forbearance.mall.web;

import cn.forbearance.mall.util.AdminResponseUtil;
import cn.forbearance.mall.util.JacksonUtil;
import cn.forbearance.mall.util.ResponseUtil;
import cn.forbearance.mall.util.bcrypt.BCryptPasswordEncoder;
import cn.forbearance.mall.pojo.DtsAdmin;
import cn.forbearance.mall.service.DtsAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.forbearance.mall.util.AdminResponseCode.ADMIN_INVALID_OLD_PASSWORD;

@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {
	private static final Logger logger = LoggerFactory.getLogger(AdminProfileController.class);

	@Autowired
	private DtsAdminService adminService;

	@RequiresAuthentication
	@PostMapping("/password")
	public Object create(@RequestBody String body) {
		logger.info("【请求开始】系统管理->修改密码,请求参数,body:{}", body);

		String oldPassword = JacksonUtil.parseString(body, "oldPassword");
		String newPassword = JacksonUtil.parseString(body, "newPassword");
		if (StringUtils.isEmpty(oldPassword)) {
			return ResponseUtil.badArgument();
		}
		if (StringUtils.isEmpty(newPassword)) {
			return ResponseUtil.badArgument();
		}

		Subject currentUser = SecurityUtils.getSubject();
		DtsAdmin admin = (DtsAdmin) currentUser.getPrincipal();

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(oldPassword, admin.getPassword())) {
			logger.info("系统管理->修改密码 错误:{}", ADMIN_INVALID_OLD_PASSWORD.desc());
			return AdminResponseUtil.fail(ADMIN_INVALID_OLD_PASSWORD);
		}

		String encodedNewPassword = encoder.encode(newPassword);
		admin.setPassword(encodedNewPassword);

		adminService.updateById(admin);

		logger.info("【请求结束】系统管理->修改密码,响应结果:{}", "成功!");
		return ResponseUtil.ok();
	}

}
