package com.xtgg.utils;

import net.sf.json.JSONObject;

/**
 * 工具类
 *
 */
public class StringUtils {

	public static String encapsulatedSussessJSON(String msg, String data) {
		JSONObject json = new JSONObject();
		json.accumulate("code", Constants.INTERFACE_SUCCESS);
		json.accumulate("msg", msg);
		json.accumulate("data", data);
		return json.toString();
	}

	public static String encapsulatedFailJSON(String msg, String data) {
		JSONObject json = new JSONObject();
		json.accumulate("code", Constants.INTERFACE_FAIL);
		json.accumulate("msg", msg);
		json.accumulate("data", data);
		return json.toString();
	}

	public static String encapsulatedBadParamsJSON(String msg, String data) {
		JSONObject json = new JSONObject();
		json.accumulate("code", Constants.INTERFACE_FAIL);
		json.accumulate("msg", msg);
		json.accumulate("data", data);
		return json.toString();
	}

	public static String encapsulatedJSON(int code, String msg, String data) {
		JSONObject json = new JSONObject();
		json.accumulate("code", code);
		json.accumulate("msg", msg);
		json.accumulate("data", data);
		return json.toString();
	}

	public static String encapsulatedJSON(JSONObject json, int code,
			String msg, String data) {
		json.accumulate("code", code);
		json.accumulate("msg", msg);
		json.accumulate("data", data);
		return json.toString();
	}

	public static boolean isEmpty(String input) {
		return (input == null || input.length() == 0);
	}
}
