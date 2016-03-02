package com.xtgg.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseClientLastTime<M extends BaseClientLastTime<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setClientId(java.lang.Integer clientId) {
		set("clientId", clientId);
	}

	public java.lang.Integer getClientId() {
		return get("clientId");
	}

	public void setLastFollowTime(java.util.Date lastFollowTime) {
		set("lastFollowTime", lastFollowTime);
	}

	public java.util.Date getLastFollowTime() {
		return get("lastFollowTime");
	}

	public void setLastUploadDateTime(java.util.Date lastUploadDateTime) {
		set("lastUploadDateTime", lastUploadDateTime);
	}

	public java.util.Date getLastUploadDateTime() {
		return get("lastUploadDateTime");
	}

}