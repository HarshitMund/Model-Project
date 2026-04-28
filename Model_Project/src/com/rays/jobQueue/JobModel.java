package com.rays.jobQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select max(jobId) from jobQueue");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return pk + 1;
	}

	public long add(JobBean bean) throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			pk = nextPk();
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into jobQueue values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getJobCode());
			pstmt.setString(3, bean.getJobName());
			pstmt.setString(4, bean.getPriority());
			pstmt.setString(5, bean.getStatus());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return pk;
	}

	public void update(JobBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update jobQueue set jobCode = ?, jobName = ?, priority = ?, status = ? where jobId = ?");
			pstmt.setString(1, bean.getJobCode());
			pstmt.setString(2, bean.getJobName());
			pstmt.setString(3, bean.getPriority());
			pstmt.setString(4, bean.getStatus());
			pstmt.setLong(5, bean.getJobId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(JobBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from jobQueue where jobId = ?");
			pstmt.setLong(1, bean.getJobId());

			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows deleted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public List<JobBean> search(JobBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = null;
		List<JobBean> list = new ArrayList<JobBean>();

		StringBuffer sb = new StringBuffer("select * from jobQueue where 1 = 1");

		if (bean != null) {
			if (bean.getJobId() > 0)
				sb.append(" and jobId = " + bean.getJobId());

			if (bean.getJobCode() != null && bean.getJobCode().length() > 0)
				sb.append(" and jobCode like '" + bean.getJobCode() + "%'");

			if (bean.getJobName() != null && bean.getJobName().length() > 0)
				sb.append(" and jobName like '" + bean.getJobName() + "%'");

			if (bean.getPriority() != null && bean.getPriority().length() > 0)
				sb.append(" and priority like '" + bean.getPriority() + "%'");

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setJobId(rs.getLong(1));
				bean.setJobCode(rs.getString(2));
				bean.setJobName(rs.getString(3));
				bean.setPriority(rs.getString(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return list;
	}

	public JobBean findByPk(long pk) throws Exception {

		Connection conn = null;
		JobBean bean = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			PreparedStatement pstmt = conn.prepareStatement("select * from jobQueue where jobId = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setJobId(rs.getLong(1));
				bean.setJobCode(rs.getString(2));
				bean.setJobName(rs.getString(3));
				bean.setPriority(rs.getString(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}
}
