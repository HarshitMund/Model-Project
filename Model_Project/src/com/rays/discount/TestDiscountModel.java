package com.rays.discount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDiscountModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		DiscountBean bean = new DiscountBean();
		DiscountModel model = new DiscountModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDiscountId(16);
		bean.setDiscountCode("SUPERSALE40");
		bean.setPercentage(39.50);
		bean.setExpireDate(sdf.parse("2026-03-28"));
		bean.setStatus("ACTIVE");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);

	}

	public static void testUpdate() throws Exception {

		DiscountBean bean = new DiscountBean();
		DiscountModel model = new DiscountModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setDiscountId(16);
		bean.setDiscountCode("MEGA20");
		bean.setPercentage(20.00);
		bean.setExpireDate(sdf.parse("2026-01-28"));
		bean.setStatus("EXPIRED");

		model.update(bean);
		
	}
	
	public static void testDelete() throws Exception {

		DiscountBean bean = new DiscountBean();
		DiscountModel model = new DiscountModel();
		
		bean.setDiscountId(16);
		
		model.delete(bean);
		
	}
	
	public static void testSearch() throws Exception {
		
		DiscountBean bean = new DiscountBean();
		DiscountModel model = new DiscountModel();
		List<DiscountBean> list = new ArrayList<DiscountBean>();
		
		bean.setStatus("EXPIRED");
		list = model.search(bean, 1, 5);
		
		Iterator<DiscountBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getDiscountId());
			System.out.print("\t" + bean.getDiscountCode());
			System.out.print("\t" + bean.getPercentage());
			System.out.print("\t" + bean.getExpireDate());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
