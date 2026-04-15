package com.rays.reward;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestRewardModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		RewardModel model = new RewardModel();
		RewardBean bean = new RewardBean();

		bean.setRawardId(16);
		bean.setRewardCode("RWD016");
		bean.setRewardName("Zomato Voucher ₹100");
		bean.setPointsRequired(100);
		bean.setStatus("Active");

		long id = model.add(bean);
		System.out.println("Record inserted at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		RewardModel model = new RewardModel();
		RewardBean bean = new RewardBean();

		bean.setRawardId(16);
		bean.setRewardCode("RWD016");
		bean.setRewardName("Gpay Voucher ₹50");
		bean.setPointsRequired(50);
		bean.setStatus("Inactive");

		model.update(bean);
	}

	public static void testDelete() throws Exception {

		RewardModel model = new RewardModel();
		RewardBean bean = new RewardBean();

		bean.setRawardId(16);

		model.delete(bean);
	}

	public static void testSearch() throws Exception {

		List<RewardBean> list = new ArrayList<RewardBean>();

		RewardBean bean = new RewardBean();
		RewardModel model = new RewardModel();
		
		bean.setStatus("Active");

		list = model.search(bean, 1, 5);

		Iterator<RewardBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.print(bean.getRawardId());
			System.out.print("\t" + bean.getRewardCode());
			System.out.print("\t" + bean.getRewardName());
			System.out.print("\t" + bean.getPointsRequired());
			System.out.println("\t" + bean.getStatus());
		}
	}
}
