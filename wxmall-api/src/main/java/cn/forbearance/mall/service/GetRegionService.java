package cn.forbearance.mall.service;

import cn.forbearance.mall.pojo.DtsRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiguliuxing
 * @since 1.0.0
 * @date 2017-04-11 11:07
 **/
@Component
public class GetRegionService {

	@Autowired
	private DtsRegionService regionService;

	private static List<DtsRegion> DtsRegions;

	protected List<DtsRegion> getDtsRegions() {
		if (DtsRegions == null) {
			createRegion();
		}
		return DtsRegions;
	}

	private synchronized void createRegion() {
		if (DtsRegions == null) {
			DtsRegions = regionService.getAll();
		}
	}
}
