package com.rays.notificationRule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RuleModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select max(ruleId) from notificationRule");

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public void add(RuleBean bean) throws Exception {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into notificationRule values (?, ?, ?, ?, ?)");
			pstm.setLong(1, nextPk());
			pstm.setString(2, bean.getRuleCode());
			pstm.setString(3, bean.getEvent());
			pstm.setString(4, bean.getTriggerType());
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

	}

	public void update(RuleBean bean) throws Exception {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update notificationRule set ruleCode = ?, event = ?, triggeredType = ?, status = ? where ruleId = ?");
			pstm.setString(1, bean.getRuleCode());
			pstm.setString(2, bean.getEvent());
			pstm.setString(3, bean.getTriggerType());
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

	public void delete(RuleBean bean) throws Exception {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from notificationRule where ruleId = ?");
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

	public List<RuleBean> search(RuleBean bean, int pageNo, int pageSize) throws Exception {

		List<RuleBean> list = new ArrayList<RuleBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("Select * from notificationRule where 1 = 1");

		if (bean != null) {
			if (bean.getRuleId() > 0)
				sb.append(" and ruleId = " + bean.getRuleId());

			if (bean.getRuleCode() != null && bean.getRuleCode().length() > 0)
				sb.append(" and ruleCode like '" + bean.getRuleCode() + "%'");

			if (bean.getEvent() != null && bean.getEvent().length() > 0)
				sb.append(" and event like '" + bean.getEvent() + "%'");

			if (bean.getTriggerType() != null && bean.getTriggerType().length() > 0)
				sb.append(" and triggeredType like '" + bean.getTriggerType() + "%'");

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
			bean = new RuleBean();

			bean.setRuleId(rs.getLong(1));
			bean.setRuleCode(rs.getString(2));
			bean.setEvent(rs.getString(3));
			bean.setTriggerType(rs.getString(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}
}
