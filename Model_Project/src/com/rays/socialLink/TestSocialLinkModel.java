package com.rays.socialLink;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSocialLinkModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}

	public static void testAdd() throws Exception {

		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel linkModel = new SocialLinkModel();

		bean.setSocialId(16);
		bean.setSocialCode("SOC016");
		bean.setUserName("Get_It_Framed");
		bean.setPlatform("Instagram");
		bean.setStatus("Inactive");

		long id = linkModel.add(bean);
		System.out.println("record add at ID: " + id);
	}

	public static void testUpdate() throws Exception {

		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel linkModel = new SocialLinkModel();

		bean.setSocialId(16);
		bean.setSocialCode("SOC016");
		bean.setUserName("HellBoy");
		bean.setPlatform("Instagram");
		bean.setStatus("Active");

		linkModel.update(bean);
	}

	public static void testDelete() throws Exception {

		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel linkModel = new SocialLinkModel();

		bean.setSocialId(16);

		linkModel.delete(bean);
	}

	public static void testSearch() throws Exception {

		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel linkModel = new SocialLinkModel();
		List<SocialLinkBean> list = new ArrayList<SocialLinkBean>();

		bean.setStatus("Inactive");

		list = linkModel.search(bean, 1, 5);
		
		Iterator<SocialLinkBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getSocialId());
			System.out.print("\t" + bean.getSocialCode());
			System.out.print("\t" + bean.getUserName());
			System.out.print("\t" + bean.getPlatform());
			System.out.println("\t" + bean.getStatus());
		}
	}

}
