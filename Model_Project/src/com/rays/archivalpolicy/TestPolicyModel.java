package com.rays.archivalpolicy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestPolicyModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}
	
	public static void testAdd() throws Exception {
		
		PolicyBean bean = new PolicyBean();
		PolicyModel model = new PolicyModel();
		
		bean.setPolicyCode("AP016");
		bean.setDataType("Images");
		bean.setArchiveAfterDays(55);
		bean.setStatus("Active");
		
		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {
		
		PolicyBean bean = new PolicyBean();
		PolicyModel model = new PolicyModel();
		
		bean.setPolicyId(16);
		bean.setPolicyCode("AP016");
		bean.setDataType("Emails");
		bean.setArchiveAfterDays(95);
		bean.setStatus("Inactive");
		
		model.update(bean);
	}
	
	public static void testDelete() throws Exception {
		
		PolicyBean bean = new PolicyBean();
		PolicyModel model = new PolicyModel();
		
		bean.setPolicyId(16);
		
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		List<PolicyBean> list = new ArrayList<PolicyBean>();
		
		PolicyBean bean = new PolicyBean();
		PolicyModel model = new PolicyModel();
		
		bean.setStatus("Active");
		
		list = model.search(bean, 1, 5);
		
		Iterator<PolicyBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getPolicyId());
			System.out.print("\t" + bean.getPolicyCode());
			System.out.print("\t" + bean.getDataType());
			System.out.print("\t" + bean.getArchiveAfterDays());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
