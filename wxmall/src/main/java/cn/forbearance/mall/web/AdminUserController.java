package cn.forbearance.mall.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import cn.forbearance.mall.annotation.RequiresPermissionsDesc;
import cn.forbearance.mall.qcode.QCodeService;
import cn.forbearance.mall.util.JacksonUtil;
import cn.forbearance.mall.util.ResponseUtil;
import cn.forbearance.mall.validator.Order;
import cn.forbearance.mall.validator.Sort;
import cn.forbearance.mall.pojo.DtsUser;
import cn.forbearance.mall.pojo.DtsUserAccount;
import cn.forbearance.mall.service.DtsUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired
	private DtsUserService userService;
	
	@Autowired
	private QCodeService qCodeService;

	@RequiresPermissions("admin:user:list")
	@RequiresPermissionsDesc(menu = { "用户管理", "会员管理" }, button = "查询")
	@GetMapping("/list")
	public Object list(String username, String mobile, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		logger.info("【请求开始】用户管理->会员管理->查询,请求参数,username:{},code:{},page:{}", username, mobile, page);

		List<DtsUser> userList = userService.querySelective(username, mobile, page, limit, sort, order);
		long total = PageInfo.of(userList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", userList);

		logger.info("【请求结束】用户管理->会员管理->查询:响应结果:{}", JSONObject.toJSONString(data));
		return ResponseUtil.ok(data);
	}
	
	/**
	 * 订单详情
	 *
	 * @param id
	 * @return
	 */
	@RequiresPermissions("admin:user:read")
	@RequiresPermissionsDesc(menu = { "用户管理", "会员管理" }, button = "代理详情")
	@GetMapping("/detailApprove")
	public Object detailApprove(@NotNull Integer id) {
		logger.info("【请求开始】用户管理->会员管理->代理详情,请求参数:id:{}", id);
		
		DtsUserAccount dbAccount = userService.detailApproveByUserId(id);
		if (dbAccount == null) {
			logger.error("用户管理->会员管理->代理详情 错误：userID:{},{}",id,"代理账号信息不存在");
			ResponseUtil.badArgumentValue();
		}
		logger.info("【请求结束】用户管理->会员管理->代理详情:响应结果:{}", JSONObject.toJSONString(dbAccount));
		return ResponseUtil.ok(dbAccount);
	}
	
	@RequiresPermissions("admin:user:approveAgency")
	@RequiresPermissionsDesc(menu = { "用户管理", "会员管理" }, button = "代理审批")
	@PostMapping("/approveAgency")
	public Object approveAgency(@RequestBody String body) {
		logger.info("【请求开始】用户管理->会员管理->代理审批,请求参数:{}",body);
		
		Integer userId = JacksonUtil.parseInteger(body, "userId");
		Integer settlementRate = JacksonUtil.parseInteger(body, "settlementRate");
		
		if (userId == null || settlementRate == null || settlementRate.intValue() <= 0 ){
			return ResponseUtil.badArgument();
		}
		try {
			/*
			 * 生成代理用户独有分享的二维码需要小程序已经上线，所以未上线小程序这里调用会异常
			 * 建议通过后台参数控制，因为定制用户对这里的特殊性要求，本程序暂不做调整
			 */
			String shareUrl = qCodeService.createShareUserImage(userId);
			
			userService.approveAgency(userId,settlementRate,shareUrl);
		}catch (Exception e) {
			logger.error("用户管理->会员管理->代理审批 出错：{}",e.getMessage());
			e.printStackTrace();
		}
		
		logger.info("【请求结束】用户管理->会员管理->代理审批:响应结果:{}", "成功!");
		return ResponseUtil.ok();
	}
	
}
