package cn.forbearance.mall.service;

import com.github.pagehelper.PageHelper;
import cn.forbearance.mall.dao.DtsFeedbackMapper;
import cn.forbearance.mall.pojo.DtsFeedback;
import cn.forbearance.mall.pojo.DtsFeedbackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author qiguliuxing
 * @date 2017/12/27
 */
@Service
public class DtsFeedbackService {
	@Autowired
	private DtsFeedbackMapper feedbackMapper;

	public Integer add(DtsFeedback feedback) {
		feedback.setAddTime(LocalDateTime.now());
		feedback.setUpdateTime(LocalDateTime.now());
		return feedbackMapper.insertSelective(feedback);
	}

	public List<DtsFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort,
			String order) {
		DtsFeedbackExample example = new DtsFeedbackExample();
		DtsFeedbackExample.Criteria criteria = example.createCriteria();

		if (userId != null) {
			criteria.andUserIdEqualTo(userId);
		}
		if (!StringUtils.isEmpty(username)) {
			criteria.andUsernameLike("%" + username + "%");
		}
		criteria.andDeletedEqualTo(false);

		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			example.setOrderByClause(sort + " " + order);
		}

		PageHelper.startPage(page, limit);
		return feedbackMapper.selectByExample(example);
	}
}
