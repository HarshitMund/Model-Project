package com.rays.subscriptionUsage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(usageId) from subscriptionUsage");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return pk + 1;
	}

	public long add(SubscriptionBean bean) throws Exception {

		long pk = 0;
		Connection conn = null;

		SubscriptionBean existingBean = findByName(bean.getUserName());
		if (existingBean != null) {
			throw new Exception("User Name already exist....");
		}

		SubscriptionBean duplicateBean = findByCode(bean.getUsageCode());
		if (duplicateBean != null) {
			throw new Exception("Usage code already exist....");
		}

		try {
			pk = nextPk();
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into subscriptionUsage values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getUsageCode());
			pstmt.setString(3, bean.getUserName());
			pstmt.setInt(4, bean.getUsageCount());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return pk;
	}

	public void update(SubscriptionBean bean) throws Exception {

		Connection conn = null;

		SubscriptionBean existingBean = findByName(bean.getUserName());
		if (existingBean != null) {
			throw new Exception("User Name already exist....");
		}

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update subscriptionUsage set usageCode = ?, userName = ?, usageCount = ?, status = ? where usageId = ?");
			pstmt.setString(1, bean.getUsageCode());
			pstmt.setString(2, bean.getUserName());
			pstmt.setInt(3, bean.getUsageCount());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getUsageId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(SubscriptionBean bean) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from subscriptionUsage where usageId = ?");
			pstmt.setLong(1, bean.getUsageId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows delete)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public SubscriptionBean findByPk(long pk) throws Exception {

		Connection conn = null;
		SubscriptionBean bean = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from subscriptionUsage where usageId = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubscriptionBean();
				bean.setUsageId(rs.getLong(1));
				bean.setUsageCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setUsageCount(rs.getInt(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

	public SubscriptionBean findByCode(String code) throws Exception {

		Connection conn = null;
		SubscriptionBean bean = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from subscriptionUsage where usageCode = ?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubscriptionBean();
				bean.setUsageId(rs.getLong(1));
				bean.setUsageCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setUsageCount(rs.getInt(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

	public SubscriptionBean findByName(String name) throws Exception {

		Connection conn = null;
		SubscriptionBean bean = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from subscriptionUsage where userName = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubscriptionBean();
				bean.setUsageId(rs.getLong(1));
				bean.setUsageCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setUsageCount(rs.getInt(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

	public List<SubscriptionBean> search(SubscriptionBean bean, int pageNo, int pageSize) throws Exception {

		List<SubscriptionBean> list = new ArrayList<SubscriptionBean>();
		Connection conn = null;

		StringBuffer sb = new StringBuffer("select * from subscriptionUsage where 1 = 1");

		if (bean != null) {
			if (bean.getUsageId() > 0)
				sb.append(" and usageId = " + bean.getUsageId());

			if (bean.getUsageCode() != null && bean.getUsageCode().length() > 0)
				sb.append(" and usageCode like '" + bean.getUsageCode() + "%'");

			if (bean.getUserName() != null && bean.getUserName().length() > 0)
				sb.append(" and userName like '" + bean.getUserName() + "%'");

			if (bean.getUsageCount() > 0)
				sb.append(" and usageCount = " + bean.getUsageCount());

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubscriptionBean();
				bean.setUsageId(rs.getLong(1));
				bean.setUsageCode(rs.getString(2));
				bean.setUserName(rs.getString(3));
				bean.setUsageCount(rs.getInt(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return list;
	}

}
