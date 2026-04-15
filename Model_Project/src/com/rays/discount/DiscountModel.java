package com.rays.discount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DiscountModel {

	public long add(DiscountBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into discount values (?, ?, ?, ?, ?)");

			pstm.setLong(1, bean.getDiscountId());
			pstm.setString(2, bean.getDiscountCode());
			pstm.setDouble(3, bean.getPercentage());
			pstm.setDate(4, new Date(bean.getExpireDate().getTime()));
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

		return bean.getDiscountId();

	}

	public void update(DiscountBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update discount set discountCode = ?, percentage = ?, expiryDate = ?, status = ? where discountId = ?");

			pstm.setString(1, bean.getDiscountCode());
			pstm.setDouble(2, bean.getPercentage());
			pstm.setDate(3, new Date(bean.getExpireDate().getTime()));
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getDiscountId());

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

	public void delete(DiscountBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"delete from discount where discountId = ?");

			pstm.setLong(1, bean.getDiscountId());

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
	
	public List<DiscountBean> search(DiscountBean bean, int pageNo, int pageSize) throws Exception {
		
		List<DiscountBean> list = new ArrayList<DiscountBean>();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
		
		StringBuffer sb = new StringBuffer("select * from discount where 1 = 1");
		
		if(bean != null) {
			if(bean.getDiscountId() > 0)
				sb.append(" and discountId = " + bean.getDiscountId());
			
			if(bean.getDiscountCode() != null && bean.getDiscountCode().length() > 0)
				sb.append(" and discountCode like '" + bean.getDiscountCode() + "%'");
			
			if(bean.getPercentage() > 0)
				sb.append(" and percentage =  " + bean.getPercentage());
			
			if(bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}
		
		if(pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo +", " + pageSize);
		}
		
		PreparedStatement pstm = conn.prepareStatement(sb.toString());
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			bean = new DiscountBean();
			bean.setDiscountId(rs.getLong(1));
			bean.setDiscountCode(rs.getString(2));
			bean.setPercentage(rs.getDouble(3));
			bean.setExpireDate(rs.getDate(4));
			bean.setStatus(rs.getString(5));
			list.add(bean);
		}
		
		pstm.close();
		conn.close();
		
		return list;
	}

}
