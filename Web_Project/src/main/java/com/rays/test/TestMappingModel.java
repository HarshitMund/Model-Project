package com.rays.test;

import java.util.List;

import com.rays.bean.MappingBean;
import com.rays.model.MappingModel;

public class TestMappingModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
		testSearch();
	}

	public static void testAdd() throws Exception {

		MappingBean bean = new MappingBean();
		bean.setCode("DM100");
		bean.setSourceField("test_source");
		bean.setTargetField("test_target");
		bean.setStatus("Active");

		MappingModel model = new MappingModel();
		long pk = model.add(bean);

		System.out.println("Data Inserted with PK = " + pk);
	}

	public static void testUpdate() throws Exception {

		MappingModel model = new MappingModel();
		MappingBean bean = model.findByPk(16);

		if (bean != null) {
			bean.setCode("DM101");
			bean.setSourceField("updated_source");
			bean.setTargetField("updated_target");
			bean.setStatus("Inactive");

			model.update(bean);
			System.out.println("Data Updated...");
		} else {
			System.out.println("Record not found for update");
		}
	}

	public static void testDelete() throws Exception {

		MappingBean bean = new MappingBean();
		bean.setId(16);

		MappingModel model = new MappingModel();
		model.delete(bean);

		System.out.println("Data Deleted...");
	}

	public static void testFindByPk() throws Exception {

		MappingModel model = new MappingModel();
		MappingBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.println("ID: " + bean.getId());
			System.out.println("Code: " + bean.getCode());
			System.out.println("Source: " + bean.getSourceField());
			System.out.println("Target: " + bean.getTargetField());
			System.out.println("Status: " + bean.getStatus());
		} else {
			System.out.println("No record found");
		}
	}

	public static void testSearch() throws Exception {

		MappingModel model = new MappingModel();
		MappingBean bean = new MappingBean();

		bean.setStatus("Active"); 

		List<MappingBean> list = model.search(bean, 1, 10);

		for (MappingBean b : list) {
			System.out.print(b.getId() + " | ");
			System.out.print(b.getCode() + " | ");
			System.out.print(b.getSourceField() + " | ");
			System.out.print(b.getTargetField() + " | ");
			System.out.println(b.getStatus());
		}
	}
}