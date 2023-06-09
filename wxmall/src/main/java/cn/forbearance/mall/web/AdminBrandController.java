package cn.forbearance.mall.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import cn.forbearance.mall.annotation.RequiresPermissionsDesc;
import cn.forbearance.mall.util.ResponseUtil;
import cn.forbearance.mall.validator.Order;
import cn.forbearance.mall.validator.Sort;
import cn.forbearance.mall.pojo.DtsBrand;
import cn.forbearance.mall.service.DtsBrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/brand")
@Validated
public class AdminBrandController {
	private static final Logger logger = LoggerFactory.getLogger(AdminBrandController.class);

	@Autowired
	private DtsBrandService brandService;

	@RequiresPermissions("admin:brand:list")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "查询")
	@GetMapping("/list")
	public Object list(String id, String name, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		logger.info("【请求开始】商场管理->品牌管理->查询,请求参数:name:{},page:{}", name, page);

		List<DtsBrand> brandList = brandService.querySelective(id, name, page, limit, sort, order);
		long total = PageInfo.of(brandList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", brandList);

		logger.info("【请求结束】商场管理->品牌管理->查询:total:{}", total);
		return ResponseUtil.ok(data);
	}

	private Object validate(DtsBrand brand) {
		String name = brand.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}

		String desc = brand.getDesc();
		if (StringUtils.isEmpty(desc)) {
			return ResponseUtil.badArgument();
		}

		BigDecimal price = brand.getFloorPrice();
		if (price == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@RequiresPermissions("admin:brand:create")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody DtsBrand brand) {
		logger.info("【请求开始】商场管理->品牌管理->添加,请求参数:{}", JSONObject.toJSONString(brand));
		Object error = validate(brand);
		if (error != null) {
			return error;
		}
		brandService.add(brand);
		logger.info("【请求结束】商场管理->品牌管理->添加:响应结果:{}", JSONObject.toJSONString(brand));
		return ResponseUtil.ok(brand);
	}

	@RequiresPermissions("admin:brand:read")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "详情")
	@GetMapping("/read")
	public Object read(@NotNull Integer id) {
		logger.info("【请求开始】商场管理->品牌管理->详情,请求参数, id:{}", id);

		DtsBrand brand = brandService.findById(id);

		logger.info("【请求结束】商场管理->品牌管理->详情:响应结果:{}", JSONObject.toJSONString(brand));
		return ResponseUtil.ok(brand);
	}

	@RequiresPermissions("admin:brand:update")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody DtsBrand brand) {
		logger.info("【请求开始】商场管理->品牌管理->编辑,请求参数, id:{}", JSONObject.toJSONString(brand));

		Object error = validate(brand);
		if (error != null) {
			return error;
		}
		if (brandService.updateById(brand) == 0) {
			logger.error("商场管理->品牌管理->编辑 失败，更新数据失败！");
			return ResponseUtil.updatedDataFailed();
		}

		logger.info("【请求结束】商场管理->品牌管理->编辑:响应结果:{}", JSONObject.toJSONString(brand));
		return ResponseUtil.ok(brand);
	}

	@RequiresPermissions("admin:brand:delete")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody DtsBrand brand) {
		logger.info("【请求开始】商场管理->品牌管理->删除,请求参数:{}", JSONObject.toJSONString(brand));

		Integer id = brand.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		brandService.deleteById(id);

		logger.info("【请求结束】商场管理->品牌管理->删除,响应结果:{}", "成功！");
		return ResponseUtil.ok();
	}

}
