package com.rays.quota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuotaModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select max(quotaId) from quota");

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public void add(QuotaBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into quota values (?, ?, ?, ?, ?)");
			pstm.setLong(1, nextPk());
			pstm.setString(2, bean.getQuotaCode());
			pstm.setString(3, bean.getUserName());
			pstm.setInt(4, bean.getLimitValue());
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

	public void update(QuotaBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update quota set quotaCode = ?, userName = ?, limitValue = ?, status = ? where quotaId = ?");
			pstm.setString(1, bean.getQuotaCode());
			pstm.setString(2, bean.getUserName());
			pstm.setInt(3, bean.getLimitValue());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getQuotaId());

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

	public void delete(QuotaBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from quota where quotaId = ?");
			pstm.setLong(1, bean.getQuotaId());

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

	public List<QuotaBean> search(QuotaBean bean, int pageNo, int pageSize) throws Exception {

		List<QuotaBean> list = new ArrayList<QuotaBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from quota where 1 = 1");

		if (bean != null) {
			if (bean.getQuotaId() > 0)
				sb.append(" and quotaId = " + bean.getQuotaId());

			if (bean.getQuotaCode() != null && bean.getQuotaCode().length() > 0)
				sb.append(" and quotaCode like '" + bean.getQuotaCode() + "%'");

			if (bean.getUserName() != null && bean.getUserName().length() > 0)
				sb.append(" and userName like '" + bean.getUserName() + "%'");

			if (bean.getLimitValue() > 0)
				sb.append(" and limitValue = " + bean.getLimitValue());

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
			bean = new QuotaBean();

			bean.setQuotaId(rs.getLong(1));
			bean.setQuotaCode(rs.getString(2));
			bean.setUserName(rs.getString(3));
			bean.setLimitValue(rs.getInt(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}
}
