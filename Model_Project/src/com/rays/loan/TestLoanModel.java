package com.rays.loan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestLoanModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		LoanBean bean = new LoanBean();
		LoanModel model = new LoanModel();

		bean.setLoanId(16);
		bean.setLoanCode("LN016");
		bean.setUserName("Ashu Mohanty");
		bean.setAmount(50000.00);
		bean.setStatus("Rejected");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);

	}

	public static void testUpdate() throws Exception {

		LoanBean bean = new LoanBean();
		LoanModel model = new LoanModel();

		bean.setLoanId(16);
		bean.setLoanCode("LN016");
		bean.setUserName("Abhilash Mohanty");
		bean.setAmount(20000.00);
		bean.setStatus("Approved");

		model.update(bean);

	}
	
	public static void testDelete() throws Exception {

		LoanBean bean = new LoanBean();
		LoanModel model = new LoanModel();

		bean.setLoanId(16);
		
		model.delete(bean);

	}
	
	public static void testSearch() throws Exception {
		
		List<LoanBean> list = new ArrayList<LoanBean>();
		LoanBean bean = new LoanBean();
		LoanModel model = new LoanModel();
		
		bean.setStatus("Approved");
		list = model.search(bean, 2, 5);
		
		Iterator<LoanBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getLoanId());
			System.out.print("\t" + bean.getLoanCode());
			System.out.print("\t" + bean.getUserName());
			System.out.print("\t" + bean.getAmount());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
