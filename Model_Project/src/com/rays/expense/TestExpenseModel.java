package com.rays.expense;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestExpenseModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();
		
	}

	public static void testAdd() throws Exception {

		ExpenseBean bean = new ExpenseBean();
		ExpenseModel model = new ExpenseModel();

		bean.setExpenxeId(16);
		bean.setExpenseCode("EXP016");
		bean.setAmount(4700.00);
		bean.setCategory("Medical");
		bean.setStatus("Paid");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		ExpenseBean bean = new ExpenseBean();
		ExpenseModel model = new ExpenseModel();

		bean.setExpenxeId(16);
		bean.setExpenseCode("EXP016");
		bean.setAmount(2600.00);
		bean.setCategory("Outing");
		bean.setStatus("Pending");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		ExpenseBean bean = new ExpenseBean();
		ExpenseModel model = new ExpenseModel();

		bean.setExpenxeId(16);

		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		List<ExpenseBean> list = new ArrayList<ExpenseBean>();
		
		ExpenseBean bean = new ExpenseBean();
		ExpenseModel model = new ExpenseModel();
		
		bean.setStatus("Paid");
		
		list = model.search(bean, 1, 5);
		
		Iterator<ExpenseBean> it = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getExpenxeId());
			System.out.print("\t" + bean.getExpenseCode());
			System.out.print("\t" + bean.getAmount());
			System.out.print("\t" + bean.getCategory());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
