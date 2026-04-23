package com.rays.broadcast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestBroadcastModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}
	
	public static void testAdd() throws Exception {
		
		BroadcastBean bean = new BroadcastBean();
		BroadcastModel model = new BroadcastModel();
		
		bean.setBroadcastCode("BC016");
		bean.setMessage("Adding features");
		bean.setSentBy("Admin");
		bean.setStatus("Active");
		
		long id = model.add(bean);
		System.out.println("Record add at ID: " + id);
	}
	
	public static void testUpdate() throws Exception {
		
		BroadcastBean bean = new BroadcastBean();
		BroadcastModel model = new BroadcastModel();
		
		bean.setBroadcastId(16);
		bean.setBroadcastCode("BC016");
		bean.setMessage("Updating features");
		bean.setSentBy("System");
		bean.setStatus("Inactive");
		
		model.update(bean);
	}
	
	public static void testDelete() throws Exception {
		
		BroadcastBean bean = new BroadcastBean();
		BroadcastModel model = new BroadcastModel();
		
		bean.setBroadcastId(16);
		
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		List<BroadcastBean> list = new ArrayList<BroadcastBean>();
		BroadcastBean bean = new BroadcastBean();
		BroadcastModel model = new BroadcastModel();
		
		bean.setStatus("Active");
		list = model.search(bean, 1, 5);
		Iterator<BroadcastBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getBroadcastId());
			System.out.print("\t" + bean.getBroadcastCode());
			System.out.print("\t" + bean.getMessage());
			System.out.print("\t" + bean.getSentBy());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
