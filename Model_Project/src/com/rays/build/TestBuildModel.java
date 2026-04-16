package com.rays.build;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestBuildModel {

	public static void main(String[] args) throws Exception {

		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();

	}

	public static void testAdd() throws Exception {

		BuildBean bean = new BuildBean();
		BuildModel model = new BuildModel();

		bean.setBuildCode("BLD017");
		bean.setBuildVersion("v3.1.1");
		bean.setTriggeredBy("Developer");
		bean.setStatus("FAILED");

		model.add(bean);
	}

	public static void testUpdate() throws Exception {

		BuildBean bean = new BuildBean();
		BuildModel model = new BuildModel();

		bean.setBuildId(16);
		bean.setBuildCode("BLD016");
		bean.setBuildVersion("v3.1.0");
		bean.setTriggeredBy("Admin");
		bean.setStatus("IN_PROGRESS");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		BuildBean bean = new BuildBean();
		BuildModel model = new BuildModel();

		bean.setBuildId(16);

		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		BuildBean bean = new BuildBean();
		BuildModel model = new BuildModel();
		List<BuildBean> list = new ArrayList<BuildBean>();

		bean.setStatus("SUCCESS");

		list = model.search(bean, 1, 5);

		Iterator<BuildBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getBuildId());
			System.out.print("\t" + bean.getBuildCode());
			System.out.print("\t" + bean.getBuildVersion());
			System.out.print("\t" + bean.getTriggeredBy());
			System.out.println("\t" + bean.getStatus());
		}

	}
}
