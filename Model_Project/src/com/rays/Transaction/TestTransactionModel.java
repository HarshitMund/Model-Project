package com.rays.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestTransactionModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		TransactionBean bean = new TransactionBean();
		TransactionModel model = new TransactionModel();

		bean.setTransactionId(16);
		bean.setTransactionCode("TNX1016");
		bean.setSenderName("Siddhanta Hota");
		bean.setAmount(1231.30);
		bean.setStatus("PENDING");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);

	}

	public static void testUpdate() throws Exception {

		TransactionBean bean = new TransactionBean();
		TransactionModel model = new TransactionModel();

		bean.setTransactionId(16);
		bean.setTransactionCode("TNX1016");
		bean.setSenderName("Arushree Hota");
		bean.setAmount(1450.30);
		bean.setStatus("SUCCESS");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		TransactionBean bean = new TransactionBean();
		TransactionModel model = new TransactionModel();

		bean.setTransactionId(16);

		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		TransactionBean bean = new TransactionBean();
		TransactionModel model = new TransactionModel();
		
		List<TransactionBean> list = new ArrayList<TransactionBean>();
		
		bean.setStatus("SUCCESS");
		
		list = model.search(bean, 2, 5);
		
		Iterator<TransactionBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getTransactionId());
			System.out.print("\t" + bean.getTransactionCode());
			System.out.print("\t" + bean.getSenderName());
			System.out.print("\t" + bean.getAmount());
			System.out.println("\t" + bean.getStatus());
		}
		
	}

}
