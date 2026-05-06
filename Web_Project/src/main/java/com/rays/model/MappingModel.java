package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.MappingBean;
import com.rays.exception.DuplicaterecordException;
import com.rays.util.JDBCDataSource;

public class MappingModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from dataMapping");
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

	public long add(MappingBean bean) throws Exception {

		long pk = 0;
		Connection conn = null;

		MappingModel model = new MappingModel();
		MappingBean existingBean = model.findByCode(bean.getCode());
		if (existingBean != null) {
			throw new DuplicaterecordException("Code Already Exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into dataMapping values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getSourceField());
			pstmt.setString(4, bean.getTargetField());
			pstmt.setString(5, bean.getStatus());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void update(MappingBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update dataMapping set code = ?, sourceField = ?, targetField = ?, status = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getSourceField());
			pstmt.setString(3, bean.getTargetField());
			pstmt.setString(4, bean.getStatus());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(MappingBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from dataMapping where id = ?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows deleted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public MappingBean findByPk(long id) throws Exception {

		MappingBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from dataMapping where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MappingBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setSourceField(rs.getString(3));
				bean.setTargetField(rs.getString(4));
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

	public MappingBean findByCode(String code) throws Exception {

		MappingBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from dataMapping where code = ?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MappingBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setSourceField(rs.getString(3));
				bean.setTargetField(rs.getString(4));
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

	public List<MappingBean> search(MappingBean bean, int pageNo, int pageSize) throws Exception {

		List<MappingBean> list = new ArrayList<MappingBean>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from dataMapping where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sql.append(" and id = " + bean.getId());

			if (bean.getCode() != null && bean.getCode().length() > 0)
				sql.append(" and code like '" + bean.getCode() + "%'");

			if (bean.getSourceField() != null && bean.getSourceField().length() > 0)
				sql.append(" and sourceField like '" + bean.getSourceField() + "%'");

			if (bean.getTargetField() != null && bean.getTargetField().length() > 0)
				sql.append(" and targetField like '" + bean.getTargetField() + "%'");

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sql.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MappingBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setSourceField(rs.getString(3));
				bean.setTargetField(rs.getString(4));
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
