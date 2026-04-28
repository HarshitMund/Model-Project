package com.rays.jobQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestJobModel {

	public static JobModel model = new JobModel();

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();
		testFindByPk();

	}

	public static void testAdd() throws Exception {

		JobBean bean = new JobBean();

		bean.setJobCode("JOB016");
		bean.setJobName("Bug Fix");
		bean.setPriority("Medium");
		bean.setStatus("Completed");

		long id = model.add(bean);
		System.out.println("Record added at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		JobBean bean = new JobBean();

		bean.setJobId(16);
		bean.setJobCode("JOB016");
		bean.setJobName("Database update");
		bean.setPriority("High");
		bean.setStatus("Pending");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		JobBean bean = new JobBean();

		bean.setJobId(17);
		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		List<JobBean> list = new ArrayList<JobBean>();
		JobBean bean = new JobBean();

		list = model.search(bean, 1, 5);
		Iterator<JobBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getJobId());
			System.out.print("\t" + bean.getJobCode());
			System.out.print("\t" + bean.getJobName());
			System.out.print("\t" + bean.getPriority());
			System.out.println("\t" + bean.getStatus());
		}
	}
	
	public static void testFindByPk() throws Exception {
		
		JobBean bean = model.findByPk(1);
		
		System.out.println(bean.getJobId());
		System.out.println(bean.getJobCode());
		System.out.println(bean.getJobName());
		System.out.println(bean.getPriority());
		System.out.println(bean.getStatus());
	}

}
