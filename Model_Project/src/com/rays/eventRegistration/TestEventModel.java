package com.rays.eventRegistration;

import java.text.SimpleDateFormat;

public class TestEventModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testUpdate();
//		testDelete();
		testFindByPK();

	}

	public static void testAdd() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EventBean bean = new EventBean();

		bean.setId(1);
		bean.setPraticipant_name("Ashu");
		bean.setEvent_name("Birthday");
		bean.setEmail("ashu@gmail.com");
		bean.setRegistration_date(sdf.parse("2026-05-02"));

		EventModel model = new EventModel();
		int id = model.add(bean);
		System.out.println("Record inserted at id: " + id);

	}

	public static void testUpdate() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EventBean bean = new EventBean();

		bean.setId(1);
		bean.setPraticipant_name("Muskan");
		bean.setEvent_name("Marrage");
		bean.setEmail("muskan@gmail.com");
		bean.setRegistration_date(sdf.parse("2026-06-06"));

		EventModel model = new EventModel();
		model.update(bean);

	}

	public static void testDelete() throws Exception {

		EventBean bean = new EventBean();

		bean.setId(1);

		EventModel model = new EventModel();
		model.delete(bean);

	}

	public static void testFindByPK() throws Exception {

		EventBean bean = new EventBean();
		EventModel model = new EventModel();

		bean = model.findByPK(1);

		System.out.print(bean.getId());
		System.out.print("\t" + bean.getPraticipant_name());
		System.out.print("\t" + bean.getEvent_name());
		System.out.print("\t" + bean.getEmail());
		System.out.println("\t" + bean.getRegistration_date());

	}

}
