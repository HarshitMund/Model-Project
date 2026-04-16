package com.rays.build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BuildModel {

	public long nextPk() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		long pk = 0;

		PreparedStatement pstm = conn.prepareStatement("select max(buildId) from build");

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		pstm.close();
		conn.close();

		return pk + 1;
	}

	public void add(BuildBean bean) throws Exception {

		Connection conn = null;
		BuildBean existingBean = findByCode(bean.getBuildCode());

		if (existingBean != null) {
			throw new Exception("Build Code already existing..... Try different Code.");
		}

		existingBean = findByVersion(bean.getBuildVersion());

		if (existingBean != null) {
			throw new Exception("Build version already existing..... Try different version.");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into build values (?, ?, ?, ?, ?)");
			pstm.setLong(1, nextPk());
			pstm.setString(2, bean.getBuildCode());
			pstm.setString(3, bean.getBuildVersion());
			pstm.setString(4, bean.getTriggeredBy());
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

	public void update(BuildBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update build set buildCode = ?, buildVersion = ?, triggeredBy = ?, status = ? where buildId = ?");

			pstm.setString(1, bean.getBuildCode());
			pstm.setString(2, bean.getBuildVersion());
			pstm.setString(3, bean.getTriggeredBy());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getBuildId());

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

	public void delete(BuildBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from build where buildId = ?");

			pstm.setLong(1, bean.getBuildId());

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

	public List<BuildBean> search(BuildBean bean, int pageNo, int pageSize) throws Exception {

		List<BuildBean> list = new ArrayList<BuildBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from build where 1 = 1");

		if (bean != null) {
			if (bean.getBuildId() > 0)
				sb.append(" and buildId = " + bean.getBuildId());

			if (bean.getBuildCode() != null && bean.getBuildCode().length() > 0)
				sb.append(" and buildCode like '" + bean.getBuildCode() + "%'");

			if (bean.getBuildVersion() != null && bean.getBuildVersion().length() > 0)
				sb.append(" and buildVersion like '" + bean.getBuildVersion() + "%'");

			if (bean.getTriggeredBy() != null && bean.getTriggeredBy().length() > 0)
				sb.append(" and triggeredBy like '" + bean.getTriggeredBy() + "%'");

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
			bean = new BuildBean();

			bean.setBuildId(rs.getLong(1));
			bean.setBuildCode(rs.getString(2));
			bean.setBuildVersion(rs.getString(3));
			bean.setTriggeredBy(rs.getString(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}

	public BuildBean findByCode(String buildCode) throws Exception {

		BuildBean bean = null;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select * from build where buildCode = ?");
		pstm.setString(1, buildCode);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			bean = new BuildBean();

			bean.setBuildId(rs.getLong(1));
			bean.setBuildCode(rs.getString(2));
			bean.setBuildVersion(rs.getString(3));
			bean.setTriggeredBy(rs.getString(4));
			bean.setStatus(rs.getString(5));
		}

		pstm.close();
		conn.close();

		return bean;
	}

	public BuildBean findByVersion(String version) throws Exception {

		BuildBean bean = null;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select * from build where buildVersion = ?");
		pstm.setString(1, version);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			bean = new BuildBean();

			bean.setBuildId(rs.getLong(1));
			bean.setBuildCode(rs.getString(2));
			bean.setBuildVersion(rs.getString(3));
			bean.setTriggeredBy(rs.getString(4));
			bean.setStatus(rs.getString(5));
		}

		return bean;
	}

}
