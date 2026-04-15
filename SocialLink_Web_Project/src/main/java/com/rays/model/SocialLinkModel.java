package com.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.SocialLinkBean;
import com.rays.util.JDBCDataSource;

public class SocialLinkModel {

	public long findNextPk() throws Exception {

		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select max(socialId) from socialLink");

		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public long add(SocialLinkBean bean) throws Exception {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into socialLink values (?, ?, ?, ?, ?)");
			pstm.setLong(1, findNextPk());
			pstm.setString(2, bean.getSocialCode());
			pstm.setString(3, bean.getUserName());
			pstm.setString(4, bean.getPlatform());
			pstm.setString(5, bean.getStatus());

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

		return bean.getSocialId();
	}

	public void update(SocialLinkBean bean) throws Exception {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update socialLink set socialCode = ?, userName = ?, platform = ?, status = ? where socialId = ?");
			pstm.setString(1, bean.getSocialCode());
			pstm.setString(2, bean.getUserName());
			pstm.setString(3, bean.getPlatform());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getSocialId());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affected (rows updated)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(SocialLinkBean bean) throws Exception {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from socialLink where socialId = ?");

			pstm.setLong(1, bean.getSocialId());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affected (rows deleted)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public List<SocialLinkBean> search(SocialLinkBean bean, int pageNo, int pageSize) throws Exception {

		List<SocialLinkBean> list = new ArrayList<SocialLinkBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from socialLink where 1 = 1");

		if (bean != null) {
			if (bean.getSocialCode() != null && bean.getSocialCode().length() > 0)
				sb.append(" and socialCode like '" + bean.getSocialCode() + "%'");

			if (bean.getUserName() != null && bean.getUserName().length() > 0)
				sb.append(" and userName like '" + bean.getUserName() + "%'");

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		PreparedStatement pstm = conn.prepareStatement(sb.toString());

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			bean = new SocialLinkBean();

			bean.setSocialId(rs.getLong(1));
			bean.setSocialCode(rs.getString(2));
			bean.setUserName(rs.getString(3));
			bean.setPlatform(rs.getString(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}
	
	public SocialLinkBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from socialLink where socialId = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		SocialLinkBean bean = null;

		while (rs.next()) {
			bean = new SocialLinkBean();
			bean.setSocialId(rs.getInt(1));
			bean.setSocialCode(rs.getString(2));
			bean.setUserName(rs.getString(3));
			bean.setPlatform(rs.getString(4));
			bean.setStatus(rs.getString(5));
		}

		return bean;

	}

}
