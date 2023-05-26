package cn.forbearance.mall.dao.ex;

import cn.forbearance.mall.pojo.DtsOrder;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface OrderMapper {
	int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime,
			@Param("order") DtsOrder order);
}