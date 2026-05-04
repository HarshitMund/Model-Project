package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.TransformationBean;
import com.rays.exception.DuplicaterecordException;
import com.rays.util.JDBCDataSource;

public class TransformationModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from transformation");
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

	public long add(TransformationBean bean) throws Exception {

		long pk = 0;
		Connection conn = null;

		TransformationBean existionBean = findByCode(bean.getCode());
		if (existionBean != null) {
			throw new DuplicaterecordException("Transformation Code Already Exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into transformation values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getLogic());
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

	public void update(TransformationBean bean) throws SQLException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update transformation set code = ?, name = ?, logic = ?, status = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getLogic());
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

	public void delete(TransformationBean bean) throws SQLException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from transformation where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows deleted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public TransformationBean findByPk(long pk) throws Exception {

		Connection conn = null;
		TransformationBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from transformation where id = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransformationBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setLogic(rs.getString(4));
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

	public TransformationBean findByCode(String code) throws Exception {

		Connection conn = null;
		TransformationBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from transformation where code = ?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransformationBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setLogic(rs.getString(4));
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

	public List<TransformationBean> search(TransformationBean bean, int pageNo, int pageSize) {

		Connection conn = null;
		List<TransformationBean> list = new ArrayList<TransformationBean>();

		StringBuffer sb = new StringBuffer("select * from transformation where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getCode() != null && bean.getCode().length() > 0)
				sb.append(" and code like '" + bean.getCode() + "%'");

			if (bean.getName() != null && bean.getName().length() > 0)
				sb.append(" and name like '" + bean.getName() + "%'");

			if (bean.getLogic() != null && bean.getLogic().length() > 0)
				sb.append(" and logic like '" + bean.getLogic() + "%'");

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
				bean = new TransformationBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setLogic(rs.getString(4));
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
