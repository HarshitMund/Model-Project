package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.bean.JobBean;
import com.rays.exception.DuplicaterecordException;
import com.rays.util.JDBCDataSource;

public class JobModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(jobId) from scheduleJob");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	public long add(JobBean bean) throws DuplicaterecordException, SQLException {

		long pk = 0;
		Connection conn = null;

		JobBean existionBean = findByCode(bean.getJobCode());
		if (existionBean != null) {
			throw new DuplicaterecordException("Job Code Already Exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into scheduleJob values (?, ?, ?, ?, ?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getJobCode());
			pstmt.setString(3, bean.getJobName());
			pstmt.setString(4, bean.getCronExpression());
			pstmt.setString(5, bean.getStatus());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows inserted)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(JobBean bean) throws DuplicaterecordException, SQLException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update scheduleJob set jobCode = ?, jobName = ?, cronExpression = ?, status = ? where jobId = ?");
			pstmt.setLong(5, bean.getJobId());
			pstmt.setString(1, bean.getJobCode());
			pstmt.setString(2, bean.getJobName());
			pstmt.setString(3, bean.getCronExpression());
			pstmt.setString(4, bean.getStatus());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows updated)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void delete(JobBean bean) throws SQLException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from scheduleJob where jobId = ?");
			pstmt.setLong(1, bean.getJobId());
			int i = pstmt.executeUpdate();
			System.out.println(i + " rows affected (rows delete)");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public JobBean findByPk(long pk) {

		Connection conn = null;
		JobBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from scheduleJob where jobId = ?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setJobId(rs.getLong(1));
				bean.setJobCode(rs.getString(2));
				bean.setJobName(rs.getString(3));
				bean.setCronExpression(rs.getString(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public JobBean findByCode(String code) {

		Connection conn = null;
		JobBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from scheduleJob where jobCode = ?");
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setJobId(rs.getLong(1));
				bean.setJobCode(rs.getString(2));
				bean.setJobName(rs.getString(3));
				bean.setCronExpression(rs.getString(4));
				bean.setStatus(rs.getString(5));
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;
	}

	public List<JobBean> search(JobBean bean, int pageNo, int pageSize) {

		Connection conn = null;
		List<JobBean> list = new ArrayList<JobBean>();

		StringBuffer sb = new StringBuffer("select * from scheduleJob where 1 = 1");

		if (bean != null) {
			if (bean.getJobId() > 0)
				sb.append(" and jobId = " + bean.getJobId());

			if (bean.getJobCode() != null && bean.getJobCode().length() > 0)
				sb.append(" and jobCode like '" + bean.getJobCode() + "%'");

			if (bean.getJobName() != null && bean.getJobName().length() > 0)
				sb.append(" and jobName like '" + bean.getJobName() + "%'");

			if (bean.getCronExpression() != null && bean.getCronExpression().length() > 0)
				sb.append(" and cronExpression like '" + bean.getCronExpression() + "%'");

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new JobBean();
				bean.setJobId(rs.getLong(1));
				bean.setJobCode(rs.getString(2));
				bean.setJobName(rs.getString(3));
				bean.setCronExpression(rs.getString(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}

}
