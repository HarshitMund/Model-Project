package com.rays.quota;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestQuotaModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}
	
	public static void testAdd() throws Exception {
		
		QuotaBean bean = new QuotaBean();
		QuotaModel model = new QuotaModel();
		
		bean.setQuotaCode("QTA016");
		bean.setUserName("Payal");
		bean.setLimitValue(120);
		bean.setStatus("Active");
		
		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {
		
		QuotaBean bean = new QuotaBean();
		QuotaModel model = new QuotaModel();
		
		bean.setQuotaId(16);
		bean.setQuotaCode("QTA016");
		bean.setUserName("Khushi");
		bean.setLimitValue(170);
		bean.setStatus("Inactive");
		
		model.update(bean);
	}
	
	public static void testDelete() throws Exception {
		
		QuotaBean bean = new QuotaBean();
		QuotaModel model = new QuotaModel();
		
		bean.setQuotaId(16);
		
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		QuotaBean bean = new QuotaBean();
		QuotaModel model = new QuotaModel();
		List<QuotaBean> list = new ArrayList<QuotaBean>();
		
		bean.setStatus("Active");
		
		list = model.search(bean, 1, 5);
		
		Iterator<QuotaBean> it = list.iterator();
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getQuotaId());
			System.out.print("\t" + bean.getQuotaCode());
			System.out.print("\t" + bean.getUserName());
			System.out.print("\t" + bean.getLimitValue());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
