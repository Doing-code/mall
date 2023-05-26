package cn.forbearance.mall.dao.ex;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface StatMapper {
	List<Map> statUser();

	List<Map> statOrder();

	List<Map> statGoods();
}