package com.xtgg.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xtgg.model.Clientinfo;
import com.xtgg.utils.DateUtils;
import com.xtgg.utils.QueryCondition;
import com.xtgg.utils.StringUtils;

/**
 * ClientInfoController
 */
public class ClientInfoController extends Controller {

	@Before(GET.class)
	public void index() {
		list();
	}

	public void list() {
		String mobile = getPara("mobile");
		String name = getPara("name");

		String productId = getPara("productId");
		Date beginTime = getParaToDate("beginTime", null);
		Date endTime = getParaToDate("endTime", null);

		Clientinfo ci = new Clientinfo();
		ci.setMobile(mobile);
		ci.setName(name);
		ci.setAvailableProduct(productId);

		QueryCondition qc = new QueryCondition();

		if (null != beginTime) {
			qc.setBeginTime(beginTime);
		}

		if (null != endTime) {
			qc.setEndTime(endTime);
		}

		Page<Record> page = Clientinfo.dao.queryObject(
				getParaToInt("pageNo", 1), 10, ci, qc);

		List<Record> list = page.getList();
		JSONObject jdata = new JSONObject();

		if (null != list && list.size() > 0) {
			JSONArray ja = new JSONArray();

			for (Record record : list) {
				JSONObject jo = new JSONObject();
				jo.put("clientId", record.get("id"));
				jo.put("clientName", record.get("userName", ""));
				jo.put("mobile", record.get("mobile", ""));
				jo.put("regTime",
						DateUtils.format(record.getDate("createTime")));
				jo.put("age", record.get("age"));
				jo.put("gender", record.get("gender", -1));
				jo.put("grade", record.get("bazzaarGrade", ""));
				jo.put("clientType", record.get("type", 0));

				jo.put("invitedType",
						isInvented(record.getStr("passivityinvite")));
				jo.put("areaId", record.get("areaId"));
				jo.put("source", record.get("source", "web"));
				jo.put("lastUploadTime",
						DateUtils.format(record.getDate("lastUploadDateTime")));
				jo.put("avaliabled", record.get("availableProduct", ""));

				ja.add(jo);
			}
			jdata.put("clientList", ja);
		} else {
			jdata.put("clientList", "[]");
		}

		String s = StringUtils.encapsulatedSussessJSON("query success",
				jdata.toString());

		renderJson(s);
	}

	/**
	 * 被邀请的类型
	 * 
	 * @param passivityinvite
	 * @return
	 */
	private String isInvented(String passivityinvite) {

		if (StringUtils.isEmpty(passivityinvite)) {
			return "";
		} else {
			if (passivityinvite.startsWith("9")) {
				return "医生邀请";
			} else {
				return "患者邀请";
			}
		}
	}

	@Before(POST.class)
	public void add() {
	}

}
