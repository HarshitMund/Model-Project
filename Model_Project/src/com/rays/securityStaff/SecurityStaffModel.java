package com.rays.securityStaff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SecurityStaffModel {

	public long add(SecurityStaffBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into securitystaff values (?, ?, ?, ?)");
			pstm.setLong(1, bean.getSecurityStaffId());
			pstm.setString(2, bean.getStaffName());
			pstm.setString(3, bean.getShift());
			pstm.setDouble(4, bean.getSalary());

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

		return bean.getSecurityStaffId();

	}

	public void update(SecurityStaffBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update securitystaff set staffName = ?, shift = ?, salary = ? where securityStaffId = ?");
			pstm.setString(1, bean.getStaffName());
			pstm.setString(2, bean.getShift());
			pstm.setDouble(3, bean.getSalary());
			pstm.setLong(4, bean.getSecurityStaffId());

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

	public void delete(SecurityStaffBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"delete from securitystaff where securityStaffId = ?");
	
			pstm.setLong(1, bean.getSecurityStaffId());

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
	
	public List<SecurityStaffBean> search(SecurityStaffBean bean, int pageNo, int pageSize) throws Exception {
		
		List<SecurityStaffBean> list = new ArrayList<SecurityStaffBean>(); 
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
		
		StringBuffer sb = new StringBuffer("select * from securitystaff where 1 = 1");
		
		if(bean != null) {
			if(bean.getSecurityStaffId() > 0)
				sb.append(" and securityStaffId = " + bean.getSecurityStaffId());
			
			if(bean.getStaffName() != null && bean.getStaffName().length() > 0)
				sb.append(" and staffName like '" + bean.getStaffName() + "%'");
			
			if(bean.getShift() != null && bean.getShift().length() > 0)
				sb.append(" and shift like '" + bean.getShift() + "%'");
			
			if(bean.getSalary() > 0)
				sb.append(" and salary = " + bean.getSalary());
		}
		
		if(pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}
		
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			bean = new SecurityStaffBean();
			bean.setSecurityStaffId(rs.getLong(1));
			bean.setStaffName(rs.getString(2));
			bean.setShift(rs.getString(3));
			bean.setSalary(rs.getDouble(4));
			list.add(bean);
		}
		
		pstm.close();
		conn.close();
		
		return list;
	}

}
