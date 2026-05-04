package com.rays.test;

import java.util.List;

import com.rays.bean.TransformationBean;
import com.rays.model.TransformationModel;

public class TestTransformationModel {

	public static TransformationModel model = new TransformationModel();

	public static void main(String[] args) throws Exception {
//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testFindByCode();
		testSearch();
	}

	public static void testAdd() throws Exception {
		TransformationBean bean = new TransformationBean();

		bean.setCode("TR100");
		bean.setName("Test Transformation");
		bean.setLogic("Test Logic");
		bean.setStatus("Active");

		long pk = model.add(bean);
		System.out.println("Data inserted with PK = " + pk);
	}

	public static void testUpdate() throws Exception {
		TransformationBean bean = new TransformationBean();

		bean.setId(1);
		bean.setCode("TR001_UPDATED");
		bean.setName("Updated Name");
		bean.setLogic("Updated Logic");
		bean.setStatus("Inactive");

		model.update(bean);
		System.out.println("Data Updated");
	}

	public static void testDelete() throws Exception {
		TransformationBean bean = new TransformationBean();

		bean.setId(16); 
		model.delete(bean);

		System.out.println("Data Deleted");
	}

	public static void testFindByPk() throws Exception {
		TransformationBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getLogic());
			System.out.println(bean.getStatus());
		} else {
			System.out.println("No record found");
		}
	}

	public static void testFindByCode() throws Exception {
		TransformationBean bean = model.findByCode("TR002");

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getCode());
			System.out.println(bean.getName());
			System.out.println(bean.getLogic());
			System.out.println(bean.getStatus());
		} else {
			System.out.println("No record found");
		}
	}

	public static void testSearch() throws Exception {
		TransformationBean bean = new TransformationBean();

		bean.setStatus("Active"); 

		List<TransformationBean> list = model.search(bean, 1, 5);

		for (TransformationBean b : list) {
			System.out.println(b.getId());
			System.out.println(b.getCode());
			System.out.println(b.getName());
			System.out.println(b.getLogic());
			System.out.println(b.getStatus());
			System.out.println("----------------------");
		}
	}
}