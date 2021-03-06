package com.xtgg.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseClientFamilyRelation<M extends BaseClientFamilyRelation<M>> extends Model<M> implements IBean {

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

	public void setFamilyId(java.lang.Integer familyId) {
		set("familyId", familyId);
	}

	public java.lang.Integer getFamilyId() {
		return get("familyId");
	}

	public void setFamilyRelation(java.lang.String familyRelation) {
		set("familyRelation", familyRelation);
	}

	public java.lang.String getFamilyRelation() {
		return get("familyRelation");
	}

	public void setShortMessage(java.lang.Integer shortMessage) {
		set("shortMessage", shortMessage);
	}

	public java.lang.Integer getShortMessage() {
		return get("shortMessage");
	}

	public void setFamilyName(java.lang.String familyName) {
		set("familyName", familyName);
	}

	public java.lang.String getFamilyName() {
		return get("familyName");
	}

}
