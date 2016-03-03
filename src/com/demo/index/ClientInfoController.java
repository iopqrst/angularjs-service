package com.demo.index;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xtgg.model.Clientinfo;

/**
 * ClientInfoController
 */
public class ClientInfoController extends Controller {

	@Before(GET.class)
	public void index() {
		list();
	}

	@Before(GET.class)
	public void list() {
		Page<Record> page = Clientinfo.dao.paginate(getParaToInt(0, 1), 10);
		List<Record> list = page.getList();
		System.out.println(list.size());
		renderJson("");
	}

	@Before(POST.class)
	public void add() {
	}

	public static void main(String[] args) {
		Clientinfo ci = new Clientinfo();
		ci.setName("monkey");
		ci.setAge(23);

		List<Clientinfo> list = new ArrayList<Clientinfo>();
		list.add(ci);

		String s = JSON.toJSONString(list);
		System.out.println(s);
	}

}
