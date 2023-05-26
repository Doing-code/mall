package cn.forbearance.mall.service;

import cn.forbearance.mall.dao.DtsSystemMapper;
import cn.forbearance.mall.pojo.DtsSystem;
import cn.forbearance.mall.pojo.DtsSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DtsSystemConfigService {
	@Resource
	private DtsSystemMapper systemMapper;

	public List<DtsSystem> queryAll() {
		DtsSystemExample example = new DtsSystemExample();
		example.or();
		return systemMapper.selectByExample(example);
	}
}
