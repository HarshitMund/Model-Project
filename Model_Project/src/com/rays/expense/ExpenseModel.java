package com.rays.expense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExpenseModel {

	public long add(ExpenseBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into expense values (?, ?, ?, ?, ?)");

			pstm.setLong(1, bean.getExpenxeId());
			pstm.setString(2, bean.getExpenseCode());
			pstm.setDouble(3, bean.getAmount());
			pstm.setString(4, bean.getCategory());
			pstm.setString(5, bean.getStatus());

			int i = pstm.executeUpdate();
			System.out.println(i + "rows affected(rows inserted)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return bean.getExpenxeId();
	}

	public void update(ExpenseBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update expense set expenseCode = ?, amount = ?, category = ?, status = ? where expenseId = ?");

			pstm.setString(1, bean.getExpenseCode());
			pstm.setDouble(2, bean.getAmount());
			pstm.setString(3, bean.getCategory());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getExpenxeId());

			int i = pstm.executeUpdate();
			System.out.println(i + "rows affected(rows updated)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(ExpenseBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from expense where expenseId = ?");

			pstm.setLong(1, bean.getExpenxeId());

			int i = pstm.executeUpdate();
			System.out.println(i + "rows affected(rows deleted)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public List<ExpenseBean> search(ExpenseBean bean, int pageNo, int pageSize) throws Exception {

		List<ExpenseBean> list = new ArrayList<ExpenseBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from expense where 1 = 1");

		if (bean != null) {
			if (bean.getExpenxeId() > 0)
				sb.append(" and expenseId = " + bean.getExpenxeId());

			if (bean.getExpenseCode() != null && bean.getExpenseCode().length() > 0)
				sb.append(" and expenseCode like '" + bean.getExpenseCode() + "%'");

			if (bean.getAmount() > 0)
				sb.append(" and amount = " + bean.getAmount());

			if (bean.getCategory() != null && bean.getCategory().length() > 0)
				sb.append(" and category like '" + bean.getCategory() + "%'");

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
			bean = new ExpenseBean();

			bean.setExpenxeId(rs.getLong(1));
			bean.setExpenseCode(rs.getString(2));
			bean.setAmount(rs.getDouble(3));
			bean.setCategory(rs.getString(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}

}
