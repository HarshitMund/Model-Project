package com.rays.loan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoanModel {

	public long add(LoanBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into loan values (?, ?, ?, ?, ?)");

			pstm.setLong(1, bean.getLoanId());
			pstm.setString(2, bean.getLoanCode());
			pstm.setString(3, bean.getUserName());
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

		return bean.getLoanId();
	}

	public void update(LoanBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update loan set loanCode = ?, userName = ?, amount = ?, status = ? where loanId = ?");

			pstm.setString(1, bean.getLoanCode());
			pstm.setString(2, bean.getUserName());
			pstm.setDouble(3, bean.getAmount());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getLoanId());

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

	public void delete(LoanBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from loan where loanId = ?");

			pstm.setLong(1, bean.getLoanId());

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
	
	public List<LoanBean> search(LoanBean bean, int pageNo, int pageSize) throws Exception {
		
		List<LoanBean> list = new ArrayList<LoanBean>();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
		
		StringBuffer sb = new StringBuffer("select * from loan where 1 = 1");
		
		if(bean != null) {
			if(bean.getLoanId() > 0)
				sb.append(" and loanId = " + bean.getLoanId());
			
			if(bean.getLoanCode() != null && bean.getLoanCode().length() > 0)
				sb.append(" and loanCode like '" + bean.getLoanCode() + "%'");
			
			if(bean.getUserName() != null && bean.getUserName().length() > 0)
				sb.append(" and userName like '" + bean.getUserName() + "%'");
			
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
			bean = new LoanBean();
			bean.setLoanId(rs.getLong(1));
			bean.setLoanCode(rs.getString(2));
			bean.setUserName(rs.getString(3));
			bean.setAmount(rs.getDouble(4));
			bean.setStatus(rs.getString(5));
			list.add(bean);
		}
		
		return list;
	}

}
