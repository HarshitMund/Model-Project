package com.rays.caternigModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CateringModel {

	public void CreateTable() throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			Statement stmt = conn.createStatement();
			boolean pass = stmt.execute(
					"create table catering (cateringId bigint primary key, vendorName varchar(45), menuType varchar(45), cost decimal)");

			stmt.close();
			conn.commit();
			System.out.println("Table created...");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public long add(CateringBean bean) throws Exception {

		Connection conn = null;

		CateringBean extingBean = findByPk(bean.getCateringId());

		if (extingBean != null) {
			throw new Exception("Catering Id already existed");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into catering values(?, ?, ?, ?)");

			pstm.setLong(1, bean.getCateringId());
			pstm.setString(2, bean.getVendorName());
			pstm.setString(3, bean.getMenuType());
			pstm.setDouble(4, bean.getCost());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affected (rows inserted)");

			pstm.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return bean.getCateringId();
	}

	public void update(CateringBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update catering set vendorName = ?, menuType = ?, cost = ? where cateringId = ?");

			pstm.setString(1, bean.getVendorName());
			pstm.setString(2, bean.getMenuType());
			pstm.setDouble(3, bean.getCost());
			pstm.setLong(4, bean.getCateringId());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affected(rows updated)");

			pstm.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void delete(CateringBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from catering where cateringId = ?");

			pstm.setLong(1, bean.getCateringId());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affected(rows deleted)");

			pstm.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public CateringBean findByPk(long id) throws Exception {

		CateringBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

			PreparedStatement pstm = conn.prepareStatement("select * from catering where cateringId = ?");
			pstm.setLong(1, id);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				bean = new CateringBean();
				bean.setCateringId(rs.getLong(1));
				bean.setVendorName(rs.getString(2));
				bean.setMenuType(rs.getString(3));
				bean.setCost(rs.getDouble(4));
			}

			pstm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return bean;
	}

	public List<CateringBean> search(CateringBean bean, int pageNo, int pageSize) throws Exception {

		List<CateringBean> list = new ArrayList<CateringBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from catering where 1 = 1");

		if (bean != null) {
			if (bean.getCateringId() > 0)
				sb.append(" and cateringId = " + bean.getCateringId());

			if (bean.getVendorName() != null && bean.getVendorName().length() > 0)
				sb.append(" and vendorName like '" + bean.getVendorName() + "%'");

			if (bean.getMenuType() != null && bean.getMenuType().length() > 0)
				sb.append(" and menuType like '" + bean.getMenuType() + "%'");

			if (bean.getCost() > 0)
				sb.append(" and cost = " + bean.getCost());
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		PreparedStatement pstm = conn.prepareStatement(sb.toString());

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			bean = new CateringBean();
			bean.setCateringId(rs.getLong(1));
			bean.setVendorName(rs.getString(2));
			bean.setMenuType(rs.getString(3));
			bean.setCost(rs.getDouble(4));
			list.add(bean);
		}

		pstm.close();
		conn.close();

		return list;
	}

}
