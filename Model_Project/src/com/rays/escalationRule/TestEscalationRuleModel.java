package com.rays.escalationRule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestEscalationRuleModel {
	
	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();
		
	}
	
	public static void testAdd() throws Exception {
		
		EscalationRuleBean bean = new EscalationRuleBean();
		EscalationRuleModel model = new EscalationRuleModel();
		
		bean.setRuleCode("ER016");
		bean.setLevel("Level 3");
		bean.setAssignedTo("ITSupport");
		bean.setStatus("Active");
		
		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {
		
		EscalationRuleBean bean = new EscalationRuleBean();
		EscalationRuleModel model = new EscalationRuleModel();
		
		bean.setRuleId(16);
		bean.setRuleCode("ER016");
		bean.setLevel("Level 1");
		bean.setAssignedTo("CallCenter");
		bean.setStatus("Inactive");
		
		model.update(bean);
	}
	
	public static void testDelete() throws Exception {
		
		EscalationRuleBean bean = new EscalationRuleBean();
		EscalationRuleModel model = new EscalationRuleModel();
		
		bean.setRuleId(16);
		
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		EscalationRuleBean bean = new EscalationRuleBean();
		EscalationRuleModel model = new EscalationRuleModel();
		List<EscalationRuleBean> list = new ArrayList<EscalationRuleBean>();
		
		bean.setStatus("Active");
		
		list = model.search(bean, 1, 5);
		
		Iterator<EscalationRuleBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getRuleId());
			System.out.print("\t" + bean.getRuleCode());
			System.out.print("\t" + bean.getLevel());
			System.out.print("\t" + bean.getAssignedTo());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
