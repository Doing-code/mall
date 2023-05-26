package cn.forbearance.mall.dao;

import java.io.Serializable;

public class WxLoginInfo implements Serializable {

	private static final long serialVersionUID = -7722430332896313642L;

	private String code;
	private cn.forbearance.mall.dao.UserInfo userInfo;
	private Integer shareUserId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public cn.forbearance.mall.dao.UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(cn.forbearance.mall.dao.UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getShareUserId() {
		return shareUserId;
	}

	public void setShareUserId(Integer shareUserId) {
		this.shareUserId = shareUserId;
	}
}
