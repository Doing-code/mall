package cn.forbearance.mall.web;

import cn.forbearance.mall.annotation.RequiresPermissionsDesc;
import cn.forbearance.mall.util.ResponseUtil;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/index")
public class AdminIndexController {

	@RequestMapping("/index")
	public Object index() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresGuest
	@RequestMapping("/guest")
	public Object guest() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresAuthentication
	@RequestMapping("/authn")
	public Object authn() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresUser
	@RequestMapping("/user")
	public Object user() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresRoles("admin")
	@RequestMapping("/admin")
	public Object admin() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresRoles("admin2")
	@RequestMapping("/admin2")
	public Object admin2() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresPermissions("index:permission:read")
	@RequiresPermissionsDesc(menu = { "其他", "权限测试" }, button = "权限读")
	@GetMapping("/read")
	public Object read() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresPermissions("index:permission:write")
	@RequiresPermissionsDesc(menu = { "其他", "权限测试" }, button = "权限写")
	@PostMapping("/write")
	public Object write() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

}
