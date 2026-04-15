package com.rays.securityStaff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSecurityStaffModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		SecurityStaffBean bean = new SecurityStaffBean();
		SecurityStaffModel model = new SecurityStaffModel();

		bean.setSecurityStaffId(16);
		bean.setStaffName("Ashutosh Joshi");
		bean.setShift("Night");
		bean.setSalary(20000.50);

		long id = model.add(bean);
		System.out.println("Record inserted at id: " + id);
	}

	public static void testUpdate() throws Exception {

		SecurityStaffBean bean = new SecurityStaffBean();
		SecurityStaffModel model = new SecurityStaffModel();

		bean.setSecurityStaffId(16);
		bean.setStaffName("Boddu Raghavendra");
		bean.setShift("Day");
		bean.setSalary(18000.50);

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		SecurityStaffBean bean = new SecurityStaffBean();
		SecurityStaffModel model = new SecurityStaffModel();

		bean.setSecurityStaffId(16);
		
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		SecurityStaffBean bean = new SecurityStaffBean();
		SecurityStaffModel model = new SecurityStaffModel();
		
		List<SecurityStaffBean> list = new ArrayList<SecurityStaffBean>();
		
		bean.setShift("DAY");
		
		list = model.search(bean, 1, 5);
		
		Iterator<SecurityStaffBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getSecurityStaffId());
			System.out.print("\t" + bean.getStaffName());
			System.out.print("\t" + bean.getShift());
			System.out.println("\t" + bean.getSalary());
		}
				
	}

}
