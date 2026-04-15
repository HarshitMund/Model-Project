package com.rays.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionModel {

	public long add(TransactionBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into transaction values(?, ?, ?, ?, ?)");

			pstm.setLong(1, bean.getTransactionId());
			pstm.setString(2, bean.getTransactionCode());
			pstm.setString(3, bean.getSenderName());
			pstm.setDouble(4, bean.getAmount());
			pstm.setString(5, bean.getStatus());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affected(rows inserted)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return bean.getTransactionId();
	}

	public void update(TransactionBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update transaction set transactionCode = ?, seanderName = ?, amount = ?, status = ? where transactionId = ? ");
			pstm.setString(1, bean.getTransactionCode());
			pstm.setString(2, bean.getSenderName());
			pstm.setDouble(3, bean.getAmount());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getTransactionId());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affectde(rows updeted)");

			pstm.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void delete(TransactionBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from transaction where transactionId = ? ");

			pstm.setLong(1, bean.getTransactionId());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows affectde(rows deleted)");

			pstm.close();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	
	public List<TransactionBean> search(TransactionBean bean, int pageNo, int pageSize) {
		
		List<TransactionBean> list = new ArrayList<TransactionBean>();
		Connection conn = null;
		
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			
			StringBuffer sb = new StringBuffer("select * from transaction where 1 = 1");
			
			if(bean != null) {
				if(bean.getTransactionId() > 0)
					sb.append(" and transactionId = " + bean.getTransactionId());
				
				if(bean.getTransactionCode() != null && bean.getTransactionCode().length() > 0)
					sb.append(" and transactionCode like '" + bean.getTransactionCode() + "%'");
				
				if(bean.getSenderName() != null && bean.getSenderName().length() > 0)
					sb.append(" and seanderName like '" + bean.getSenderName() + "%'");
				
				if(bean.getAmount() > 0)
					sb.append(" and amount = " + bean.getAmount());
				
				if(bean.getStatus() != null && bean.getStatus().length() > 0)
					sb.append(" and status like '" + bean.getStatus() + "%'");
			}
			
			if(pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sb.append(" limit " + pageNo + ", " + pageSize);
			}
			
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new TransactionBean();
				bean.setTransactionId(rs.getLong(1));
				bean.setTransactionCode(rs.getString(2));
				bean.setSenderName(rs.getString(3));
				bean.setAmount(rs.getDouble(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			
			pstm.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

}
