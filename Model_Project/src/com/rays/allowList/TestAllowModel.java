package com.rays.allowList;

import java.util.Iterator;
import java.util.List;

public class TestAllowModel {

	public static AllowModel model = new AllowModel();

	public static void main(String[] args) throws Exception {
		
//		add();
//		update();
//		delete();
//		findByPk();
		search();
		
	}

	public static void add() throws Exception {
		AllowBean bean = new AllowBean();

		bean.setAllowId(16);
		bean.setAllowCode("ALW016");
		bean.setAllowName("Test Entry");
		bean.setSource("Manual");
		bean.setStatus("Active");

		model.add(bean);
		System.out.println("Data Inserted...");
	}

	public static void update() throws Exception {
		AllowBean bean = new AllowBean();

		bean.setAllowId(1);
		bean.setAllowCode("ALW001");
		bean.setAllowName("Updated Admin Access");
		bean.setSource("System");
		bean.setStatus("Active");

		model.update(bean);
		System.out.println("Data Updated...");
	}

	public static void delete() throws Exception {
		AllowBean bean = new AllowBean();

		bean.setAllowId(16);

		model.delete(bean);
		System.out.println("Data Deleted...");
	}

	public static void findByPk() throws Exception {
		AllowBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getAllowId());
			System.out.println(bean.getAllowCode());
			System.out.println(bean.getAllowName());
			System.out.println(bean.getSource());
			System.out.println(bean.getStatus());
		} else {
			System.out.println("Record not found");
		}
	}

	public static void search() throws Exception {
		AllowBean bean = new AllowBean();

		List<AllowBean> list = model.search(bean, 1, 10);

		Iterator<AllowBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getAllowId());
			System.out.print("\t" + bean.getAllowCode());
			System.out.print("\t" + bean.getAllowName());
			System.out.print("\t" + bean.getSource());
			System.out.println("\t" + bean.getStatus());
		}
	}
}