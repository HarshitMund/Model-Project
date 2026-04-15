package com.rays.email;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.loan.LoanBean;

public class EmailModel {

	public long add(EmailBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement("insert into email values (?, ?, ?, ?, ?)");
			pstm.setLong(1, bean.getEmailId());
			pstm.setString(2, bean.getEmailCode());
			pstm.setString(3, bean.getToAddress());
			pstm.setString(4, bean.getSubject());
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
		return bean.getEmailId();
	}

	public void update(EmailBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(
					"update email set emailCode = ?, toAddress = ?, subject = ?, status = ? where emailId = ?");
			pstm.setString(1, bean.getEmailCode());
			pstm.setString(2, bean.getToAddress());
			pstm.setString(3, bean.getSubject());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getEmailId());
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

	public void delete(EmailBean bean) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement("delete from email where emailId = ?");
			pstm.setLong(1, bean.getEmailId());
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

	public List<EmailBean> search(EmailBean bean, int pageNo, int pageSize) throws Exception {
		List<EmailBean> list = new ArrayList<EmailBean>();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
		
		StringBuffer sb = new StringBuffer("select * from email where 1 = 1");

		if (bean != null) {
			if (bean.getEmailId() > 0)
				sb.append(" and emailId = " + bean.getEmailId());

			if (bean.getEmailCode() != null && bean.getEmailCode().length() > 0)
				sb.append(" and emailCode like '" + bean.getEmailCode() + "%'");

			if (bean.getToAddress() != null && bean.getToAddress().length() > 0)
				sb.append(" and toAddress like '" + bean.getToAddress() + "%'");

			if (bean.getSubject() != null && bean.getSubject().length() > 0)
				sb.append(" and subject = " + bean.getSubject());

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
			bean = new EmailBean();
			
			bean.setEmailId(rs.getLong(1));
			bean.setEmailCode(rs.getString(2));
			bean.setToAddress(rs.getString(3));
			bean.setSubject(rs.getString(4));
			bean.setStatus(rs.getString(5));
			
			list.add(bean);
		}

		return list;
	}

}
