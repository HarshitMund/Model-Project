package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.MappingBean;
import com.rays.bean.ParkingBean;
import com.rays.exception.DuplicaterecordException;
import com.rays.util.JDBCDataSource;

public class ParkingModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from SmartParking");
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

	public long add(ParkingBean bean) throws Exception {

		long pk = 0;
		Connection conn = null;

		ParkingBean existingBean = findByVechileNumber(bean.getVehicleNumber());
		if (existingBean != null) {
			throw new DuplicaterecordException("Vechile Number already in the parking slot");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into SmartParking values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCode());
			pstmt.setString(3, bean.getVehicleNumber());
			pstmt.setString(4, bean.getSlotNumber());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + "rows affected (rows added)");
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

	public void update(ParkingBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update SmartParking set code = ?, vechile_number = ?, slot_number = ?, status = ? where id = ?");
			pstmt.setLong(5, bean.getId());
			pstmt.setString(1, bean.getCode());
			pstmt.setString(2, bean.getVehicleNumber());
			pstmt.setString(3, bean.getSlotNumber());
			pstmt.setString(4, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + "rows affected (rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(ParkingBean bean) throws Exception {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from SmartParking where id = ?");
			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println(i + "rows affected (rows deleted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public ParkingBean findByPk(long id) throws Exception {

		Connection conn = null;
		ParkingBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from SmartParking where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ParkingBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setVehicleNumber(rs.getString(3));
				bean.setSlotNumber(rs.getString(4));
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

	public ParkingBean findByVechileNumber(String vechileNo) throws Exception {

		Connection conn = null;
		ParkingBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from SmartParking where vechile_number = ?");
			pstmt.setString(1, vechileNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ParkingBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setVehicleNumber(rs.getString(3));
				bean.setSlotNumber(rs.getString(4));
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

	public List<ParkingBean> search(ParkingBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;
		List<ParkingBean> list = new ArrayList<ParkingBean>();

		StringBuffer sb = new StringBuffer("select * from SmartParking where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0)
				sb.append(" and id = " + bean.getId());

			if (bean.getCode() != null && bean.getCode().length() > 0)
				sb.append(" and code like '" + bean.getCode() + "%'");

			if (bean.getVehicleNumber() != null && bean.getVehicleNumber().length() > 0)
				sb.append(" and vechile_number like '" + bean.getVehicleNumber() + "%'");

			if (bean.getSlotNumber() != null && bean.getSlotNumber().length() > 0)
				sb.append(" and slot_number like '" + bean.getSlotNumber() + "%'");

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
				bean = new ParkingBean();
				bean.setId(rs.getLong(1));
				bean.setCode(rs.getString(2));
				bean.setVehicleNumber(rs.getString(3));
				bean.setSlotNumber(rs.getString(4));
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
