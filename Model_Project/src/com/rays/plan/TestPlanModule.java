package com.rays.plan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestPlanModule {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		PlanBean bean = new PlanBean();
		PlanModule module = new PlanModule();

		bean.setPlanId(16);
		bean.setPlanCode("PLN_FAM_01");
		bean.setPlanName("Family Plan");
		bean.setPrice(599.99);
		bean.setStatus("INACTIVE");

		long id = module.add(bean);
		System.out.println("Record inserted at ID: " + id);

	}

	public static void testUpdate() throws Exception {

		PlanBean bean = new PlanBean();
		PlanModule module = new PlanModule();

		bean.setPlanId(16);
		bean.setPlanCode("PLN_STU_01");
		bean.setPlanName("Student Plan");
		bean.setPrice(299.99);
		bean.setStatus("INACTIVE");

		module.update(bean);

	}

	public static void testDelete() throws Exception {

		PlanBean bean = new PlanBean();
		PlanModule module = new PlanModule();

		bean.setPlanId(16);

		module.delete(bean);

	}

	public static void testSearch() throws Exception {

		List<PlanBean> list = new ArrayList<PlanBean>();
		PlanBean bean = new PlanBean();
		PlanModule module = new PlanModule();

		bean.setStatus("ACTIVE");
		list = module.search(bean, 1, 5);

		Iterator<PlanBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getPlanId());
			System.out.print("\t" + bean.getPlanCode());
			System.out.print("\t" + bean.getPlanName());
			System.out.print("\t" + bean.getPrice());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
