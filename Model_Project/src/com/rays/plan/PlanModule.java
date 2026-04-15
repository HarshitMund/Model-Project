package com.rays.plan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanModule {

	public long add(PlanBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into plan values(?, ?, ?, ?, ?)");

			pstm.setLong(1, bean.getPlanId());
			pstm.setString(2, bean.getPlanCode());
			pstm.setString(3, bean.getPlanName());
			pstm.setDouble(4, bean.getPrice());
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

		return bean.getPlanId();
	}

	public void update(PlanBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update plan set planCode = ?, planName = ?, price = ?, status = ? where planId = ?");

			pstm.setString(1, bean.getPlanCode());
			pstm.setString(2, bean.getPlanName());
			pstm.setDouble(3, bean.getPrice());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getPlanId());

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

	public void delete(PlanBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from plan where planId = ?");

			pstm.setLong(1, bean.getPlanId());

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

	public List<PlanBean> search(PlanBean bean, int pageNo, int pageSize) throws Exception {

		List<PlanBean> list = new ArrayList<PlanBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from plan where 1 = 1");

		if (bean != null) {
			if (bean.getPlanId() > 0)
				sb.append(" and planId = " + bean.getPlanId());

			if (bean.getPlanCode() != null && bean.getPlanCode().length() > 0)
				sb.append(" and planCode like '" + bean.getPlanCode() + "%'");

			if (bean.getPlanName() != null && bean.getPlanName().length() > 0)
				sb.append(" and planName like '" + bean.getPlanName() + "%'");

			if (bean.getPrice() > 0)
				sb.append(" and price = " + bean.getPrice());

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
			bean = new PlanBean();

			bean.setPlanId(rs.getLong(1));
			bean.setPlanCode(rs.getString(2));
			bean.setPlanName(rs.getString(3));
			bean.setPrice(rs.getDouble(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		pstm.close();
		conn.close();

		return list;
	}

}
