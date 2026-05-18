package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.HostelBean;
import com.rays.bean.MappingBean;
import com.rays.exception.DuplicaterecordException;
import com.rays.util.JDBCDataSource;

public class HostelModel {

	public int nextPk() throws Exception {

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from hostel");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(HostelBean bean) throws Exception {

		Connection conn = null;
		int pk = 0;

		HostelBean existBean = findByName(bean.getName());

		if (existBean != null)
			throw new DuplicaterecordException("Hostel Name already exist");

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into hostel value (?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getRooms());
			pstmt.setString(4, bean.getWardenName());
			pstmt.setDouble(5, bean.getFees());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(HostelBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("update hostel set name = ?, rooms = ?, warden_name = ?, fees = ? where id = ?");
			pstmt.setInt(5, bean.getId());
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getRooms());
			pstmt.setString(3, bean.getWardenName());
			pstmt.setDouble(4, bean.getFees());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(HostelBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from hostel where id = ?");
			pstmt.setInt(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (Rows deleted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public HostelBean findByPk(int id) throws Exception {

		HostelBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from hostel where id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new HostelBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setRooms(rs.getInt(3));
				bean.setWardenName(rs.getString(4));
				bean.setFees(rs.getDouble(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public HostelBean findByName(String name) throws Exception {

		HostelBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from hostel where name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new HostelBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setRooms(rs.getInt(3));
				bean.setWardenName(rs.getString(4));
				bean.setFees(rs.getDouble(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<HostelBean> search(HostelBean bean, int pageNo, int pageSize) throws Exception {

		List<HostelBean> list = new ArrayList<HostelBean>();
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from hostel where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sql.append(" and id = " + bean.getId());

			if (bean.getName() != null && bean.getName().length() > 0)
				sql.append(" and name like '" + bean.getName() + "%'");

			if (bean.getWardenName() != null && bean.getWardenName().length() > 0)
				sql.append(" and warden_name like '" + bean.getWardenName() + "%'");

			if (bean.getRooms() > 0)
				sql.append(" and rooms = " + bean.getRooms());

			if (bean.getFees() > 0)
				sql.append(" and fees like '" + bean.getFees());
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
				bean = new HostelBean();
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setRooms(rs.getInt(3));
				bean.setWardenName(rs.getString(4));
				bean.setFees(rs.getDouble(5));
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
