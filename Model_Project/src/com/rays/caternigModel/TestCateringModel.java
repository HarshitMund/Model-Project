package com.rays.caternigModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestCateringModel {

	public static void main(String[] args) throws Exception {

//		testCreateTable();
		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();

	}

	public static void testCreateTable() throws Exception {

		CateringModel model = new CateringModel();
		model.CreateTable();

	}

	public static void testAdd() throws Exception {

		CateringBean bean = new CateringBean();
		CateringModel model = new CateringModel();

		bean.setCateringId(15);
		bean.setVendorName("Zika");
		bean.setMenuType("Mixed");
		bean.setCost(15000.05);

		long id = model.add(bean);
		System.out.println("Record inserted at id: " + id);
	}

	public static void testUpdate() throws Exception {

		CateringBean bean = new CateringBean();
		CateringModel model = new CateringModel();

		bean.setCateringId(16);
		bean.setVendorName("Food Junction");
		bean.setMenuType("Veg");
		bean.setCost(13500.50);

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		CateringBean bean = new CateringBean();
		CateringModel model = new CateringModel();

		bean.setCateringId(15);

		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		CateringBean bean = new CateringBean();
		CateringModel model = new CateringModel();
		List<CateringBean> list = new ArrayList<CateringBean>();

		bean.setMenuType("Non-Veg");

		list = model.search(bean, 2, 3);

		Iterator<CateringBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getCateringId());
			System.out.print("\t" + bean.getVendorName());
			System.out.print("\t" + bean.getMenuType());
			System.out.println("\t" + bean.getCost());
		}

	}

}
