package cn.forbearance.mall.dao.ex;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsProductMapper {
	int addStock(@Param("id") Integer id, @Param("num") Short num);

	int reduceStock(@Param("id") Integer id, @Param("num") Short num);

	int addBrowse(@Param("id") Integer id, @Param("num") Short num);

	int addSales(@Param("id") Integer id, @Param("num") Short num);
}