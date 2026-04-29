package com.rays.subscriptionUsage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSubscriptionModel {

	public static SubscriptionModel model = new SubscriptionModel();

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();
		testFindByPk();

	}

	public static void testAdd() throws Exception {

		SubscriptionBean bean = new SubscriptionBean();

		bean.setUsageCode("USG016");
		bean.setUserName("Siddhanta Hota");
		bean.setUsageCount(5);
		bean.setStatus("Active");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		SubscriptionBean bean = new SubscriptionBean();

		bean.setUsageId(16);
		bean.setUsageCode("USG016");
		bean.setUserName("Hardik Mund");
		bean.setUsageCount(10);
		bean.setStatus("Inactive");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		SubscriptionBean bean = new SubscriptionBean();

		bean.setUsageId(16);

		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		List<SubscriptionBean> list = new ArrayList<SubscriptionBean>();
		SubscriptionBean bean = new SubscriptionBean();

		bean.setStatus("Active");

		list = model.search(bean, 1, 5);
		Iterator<SubscriptionBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getUsageId());
			System.out.print("\t" + bean.getUsageCode());
			System.out.print("\t" + bean.getUserName());
			System.out.print("\t" + bean.getUsageCount());
			System.out.println("\t" + bean.getStatus());
		}
	}

	public static void testFindByPk() throws Exception {

		SubscriptionBean bean = model.findByPk(8);

		System.out.print(bean.getUsageId());
		System.out.print("\t" + bean.getUsageCode());
		System.out.print("\t" + bean.getUserName());
		System.out.print("\t" + bean.getUsageCount());
		System.out.println("\t" + bean.getStatus());
	}

}
