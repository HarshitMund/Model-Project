package com.rays.escalationRule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EscalationRuleModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select max(ruleId) from escalationRule");

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public void add(EscalationRuleBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into escalationRule values (?, ?, ?, ?, ?)");
			pstm.setLong(1, nextPk());
			pstm.setString(2, bean.getRuleCode());
			pstm.setString(3, bean.getLevel());
			pstm.setString(4, bean.getAssignedTo());
			pstm.setString(5, bean.getStatus());

			int i = pstm.executeUpdate();
			System.out.println(i + " rows Affected (Rows inserted)");

			pstm.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void update(EscalationRuleBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update escalationRule set ruleCode = ?, level = ?, assignedTo = ?, status = ? where ruleId = ?");
			pstm.setString(1, bean.getRuleCode());
			pstm.setString(2, bean.getLevel());
			pstm.setString(3, bean.getAssignedTo());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getRuleId());

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

	public void delete(EscalationRuleBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from escalationRule where ruleId = ?");
			pstm.setLong(1, bean.getRuleId());

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

	public List<EscalationRuleBean> search(EscalationRuleBean bean, int pageNo, int pageSize) throws Exception {

		List<EscalationRuleBean> list = new ArrayList<EscalationRuleBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from escalationRule where 1 = 1");

		if (bean != null) {
			if (bean.getRuleId() > 0)
				sb.append(" and ruleId = " + bean.getRuleId());

			if (bean.getRuleCode() != null && bean.getRuleCode().length() > 0)
				sb.append(" and ruleCode like '" + bean.getRuleCode() + "%'");

			if (bean.getLevel() != null && bean.getLevel().length() > 0)
				sb.append(" and level like '" + bean.getLevel() + "%'");

			if (bean.getAssignedTo() != null && bean.getAssignedTo().length() > 0)
				sb.append(" and assignedTo like '" + bean.getAssignedTo() + "%'");

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
			bean = new EscalationRuleBean();

			bean.setRuleId(rs.getLong(1));
			bean.setRuleCode(rs.getString(2));
			bean.setLevel(rs.getString(3));
			bean.setAssignedTo(rs.getString(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}
}
