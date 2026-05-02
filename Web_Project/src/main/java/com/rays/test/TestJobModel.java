package com.rays.test;

import java.util.List;

import com.rays.bean.JobBean;
import com.rays.model.JobModel;

public class TestJobModel {

	public static void main(String[] args) throws Exception {

		testAdd();
//		testUpdate();
//		testFindByPk();
//		testSearch();
//		testDelete();
	}

	public static void testAdd() throws Exception {

		JobBean bean = new JobBean();
		bean.setJobCode("JOB101");
		bean.setJobName("Test Job");
		bean.setCronExpression("0 0 12 * *");
		bean.setStatus("ACTIVE");

		JobModel model = new JobModel();
		long pk = model.add(bean);

		System.out.println("Data Inserted with PK: " + pk);
	}

	public static void testUpdate() throws Exception {

		JobModel model = new JobModel();
		JobBean bean = model.findByPk(1); 

		if (bean != null) {
			bean.setJobCode("JOB101_UPDATED");
			bean.setJobName("Updated Job");
			bean.setCronExpression("0 30 10 * *");
			bean.setStatus("INACTIVE");

			model.update(bean);
			System.out.println("Data Updated");
		} else {
			System.out.println("Record not found for update");
		}
	}

	public static void testDelete() throws Exception {

		JobBean bean = new JobBean();
		bean.setJobId(16); 

		JobModel model = new JobModel();
		model.delete(bean);

		System.out.println("Data Deleted");
	}

	public static void testFindByPk() throws Exception {

		JobModel model = new JobModel();
		JobBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.println("ID: " + bean.getJobId());
			System.out.println("Code: " + bean.getJobCode());
			System.out.println("Name: " + bean.getJobName());
			System.out.println("Cron: " + bean.getCronExpression());
			System.out.println("Status: " + bean.getStatus());
		} else {
			System.out.println("Record not found");
		}
	}

	public static void testSearch() throws Exception {

		JobModel model = new JobModel();
		JobBean bean = new JobBean();

		bean.setStatus("ACTIVE"); 

		List<JobBean> list = model.search(bean, 1, 10);

		for (JobBean b : list) {
			System.out.println("-----------------------------");
			System.out.println("ID: " + b.getJobId());
			System.out.println("Code: " + b.getJobCode());
			System.out.println("Name: " + b.getJobName());
			System.out.println("Cron: " + b.getCronExpression());
			System.out.println("Status: " + b.getStatus());
		}
	}
}