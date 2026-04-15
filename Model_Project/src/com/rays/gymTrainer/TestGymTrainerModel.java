package com.rays.gymTrainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestGymTrainerModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		GymTrainierBean bean = new GymTrainierBean();
		GymTrainerModel model = new GymTrainerModel();

		bean.setTrainerId(16);
		bean.setTrainerName("Prateek Hota");
		bean.setSpecialization("Weight Loss");
		bean.setSalary(30000.00);

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		GymTrainierBean bean = new GymTrainierBean();
		GymTrainerModel model = new GymTrainerModel();

		bean.setTrainerId(16);
		bean.setTrainerName("Ashutosh Dash");
		bean.setSpecialization("Yoga");
		bean.setSalary(25000.00);

		model.update(bean);
	}
	
	public static void testDelete() throws Exception {

		GymTrainierBean bean = new GymTrainierBean();
		GymTrainerModel model = new GymTrainerModel();

		bean.setTrainerId(16);
		
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		GymTrainierBean bean = new GymTrainierBean();
		GymTrainerModel model = new GymTrainerModel();
		List<GymTrainierBean> list = new ArrayList<GymTrainierBean>();
		
		list = model.search(bean, 1, 5);
		
		Iterator<GymTrainierBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getTrainerId());
			System.out.print("\t" + bean.getTrainerName());
			System.out.print("\t" + bean.getSpecialization());
			System.out.println("\t" + bean.getSalary());
		}
	}

}
