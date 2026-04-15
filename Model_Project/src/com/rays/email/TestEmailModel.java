package com.rays.email;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestEmailModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();
		
	}

	public static void testAdd() throws Exception {

		EmailBean bean = new EmailBean();
		EmailModel model = new EmailModel();

		bean.setEmailId(16);
		bean.setEmailCode("EML016");
		bean.setToAddress("Sidd@example.com");
		bean.setSubject("Test Email");
		bean.setStatus("Failed");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		EmailBean bean = new EmailBean();
		EmailModel model = new EmailModel();

		bean.setEmailId(16);
		bean.setEmailCode("EML016");
		bean.setToAddress("Ashu@example.com");
		bean.setSubject("Welcome Email");
		bean.setStatus("Sent");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		EmailBean bean = new EmailBean();
		EmailModel model = new EmailModel();

		bean.setEmailId(16);

		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		List<EmailBean> list = new ArrayList<EmailBean>();
		EmailBean bean = new EmailBean();
		EmailModel model = new EmailModel();

		bean.setStatus("Sent");

		list = model.search(bean, 1, 5);
		
		Iterator<EmailBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getEmailId());
			System.out.print("\t" + bean.getEmailCode());
			System.out.print("\t" + bean.getToAddress());
			System.out.print("\t" + bean.getSubject());
			System.out.println("\t" + bean.getStatus());
		}
		
	}
}
