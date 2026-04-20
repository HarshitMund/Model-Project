package com.rays.notificationRule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestRuleModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		RuleBean bean = new RuleBean();
		RuleModel model = new RuleModel();

		bean.setRuleCode("NR016");
		bean.setEvent("Password Reset");
		bean.setTriggerType("Email");
		bean.setStatus("Active");

		model.add(bean);
	}

	public static void testUpdate() throws Exception {

		RuleBean bean = new RuleBean();
		RuleModel model = new RuleModel();

		bean.setRuleId(16);
		bean.setRuleCode("NR016");
		bean.setEvent("Follow Request");
		bean.setTriggerType("Instagram");
		bean.setStatus("Inactive");

		model.update(bean);
	}
	
	public static void testDelete() throws Exception {

		RuleBean bean = new RuleBean();
		RuleModel model = new RuleModel();

		bean.setRuleId(16);

		model.delete(bean);
	}	
	
	public static void testSearch() throws Exception {
		
		RuleBean bean = new RuleBean();
		RuleModel model = new RuleModel();
		List<RuleBean> list = new ArrayList<RuleBean>();
		
		bean.setStatus("Active");
		
		list = model.search(bean, 1, 5);
		
		Iterator<RuleBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getRuleId());
			System.out.print("\t" + bean.getRuleCode());
			System.out.print("\t" + bean.getEvent());
			System.out.print("\t" + bean.getTriggerType());
			System.out.println("\t" + bean.getStatus());
		}
		
	}

}
