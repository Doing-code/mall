package cn.forbearance.mall.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import cn.forbearance.mall.annotation.RequiresPermissionsDesc;
import cn.forbearance.mall.util.ResponseUtil;
import cn.forbearance.mall.validator.Order;
import cn.forbearance.mall.validator.Sort;
import cn.forbearance.mall.pojo.DtsCollect;
import cn.forbearance.mall.service.DtsCollectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/collect")
@Validated
public class AdminCollectController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCollectController.class);

	@Autowired
	private DtsCollectService collectService;

	@RequiresPermissions("admin:collect:list")
	@RequiresPermissionsDesc(menu = { "用户管理", "用户收藏" }, button = "查询")
	@GetMapping("/list")
	public Object list(String userId, String valueId, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		logger.info("【请求开始】用户管理->用户收藏->查询,请求参数:userId:{},page:{}", userId, page);

		List<DtsCollect> collectList = collectService.querySelective(userId, valueId, page, limit, sort, order);
		long total = PageInfo.of(collectList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", collectList);

		logger.info("【请求结束】用户管理->用户收藏->查询:total:{}", JSONObject.toJSONString(data));
		return ResponseUtil.ok(data);
	}
}
