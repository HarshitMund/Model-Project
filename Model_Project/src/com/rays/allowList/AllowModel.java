package com.rays.allowList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class AllowModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(allowId) from allowList");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public long add(AllowBean bean) throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into allowList values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getAllowCode());
			pstmt.setString(3, bean.getAllowName());
			pstmt.setString(4, bean.getSource());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(AllowBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update allowList set allowCode = ?, allowName = ?, source = ?, status = ? where allowId = ?");
			pstmt.setLong(5, bean.getAllowId());
			pstmt.setString(1, bean.getAllowCode());
			pstmt.setString(2, bean.getAllowName());
			pstmt.setString(3, bean.getSource());
			pstmt.setString(4, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(AllowBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from allowList where allowId = ?");
			pstmt.setLong(1, bean.getAllowId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows deleted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public AllowBean findByPk(long pk) throws Exception {

		Connection conn = null;
		AllowBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from allowList where allowId = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AllowBean();
				bean.setAllowId(rs.getLong(1));
				bean.setAllowCode(rs.getString(2));
				bean.setAllowName(rs.getString(3));
				bean.setSource(rs.getString(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<AllowBean> search(AllowBean bean, int pageNo, int pageSize) throws Exception {

		List<AllowBean> list = new ArrayList<AllowBean>();
		Connection conn = null;

		StringBuffer sb = new StringBuffer("select * from allowList where 1 = 1");

		if (bean != null) {
			if (bean.getAllowId() > 0)
				sb.append(" and allowId = " + bean.getAllowId());

			if (bean.getAllowCode() != null && bean.getAllowCode().length() > 0)
				sb.append(" and allowCode like '" + bean.getAllowCode() + "%'");

			if (bean.getAllowName() != null && bean.getAllowName().length() > 0)
				sb.append(" and allowName like '" + bean.getAllowName() + "%'");

			if (bean.getSource() != null && bean.getSource().length() > 0)
				sb.append(" and source like '" + bean.getSource() + "%'");

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AllowBean();
				bean.setAllowId(rs.getLong(1));
				bean.setAllowCode(rs.getString(2));
				bean.setAllowName(rs.getString(3));
				bean.setSource(rs.getString(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

}
