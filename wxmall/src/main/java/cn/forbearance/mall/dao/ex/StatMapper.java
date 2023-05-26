package cn.forbearance.mall.dao.ex;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Mapper
public interface StatMapper {
	List<Map> statUser();

	List<Map> statOrder();

	List<Map> statGoods();
}