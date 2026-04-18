package com.rays.archivalpolicy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PolicyModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select max(policyId) from archivalPolicy");

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public void add(PolicyBean bean) throws Exception {

		Connection conn = null;
		
		PolicyBean existingBean = findByCode(bean.getPolicyCode());
		
		if(existingBean != null) {
			throw new Exception("This Policy Code Already Exist... Try New Code.");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into archivalPolicy values (?, ?, ?, ?, ?)");

			pstm.setLong(1, nextPk());
			pstm.setString(2, bean.getPolicyCode());
			pstm.setString(3, bean.getDataType());
			pstm.setInt(4, bean.getArchiveAfterDays());
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
	}

	public void update(PolicyBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update archivalPolicy set policyCode = ?, dataType = ?, archiveAfterDays = ?, status = ? where policyId = ?");
			pstm.setString(1, bean.getPolicyCode());
			pstm.setString(2, bean.getDataType());
			pstm.setInt(3, bean.getArchiveAfterDays());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getPolicyId());

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

	public void delete(PolicyBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from archivalPolicy where policyId = ?");
			pstm.setLong(1, bean.getPolicyId());

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

	public List<PolicyBean> search(PolicyBean bean, int pageNo, int pageSize) throws Exception {

		List<PolicyBean> list = new ArrayList<PolicyBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from archivalPolicy where 1 = 1");

		if (bean != null) {
			if (bean.getPolicyId() > 0)
				sb.append(" and policyId = " + bean.getPolicyId());

			if (bean.getPolicyCode() != null && bean.getPolicyCode().length() > 0)
				sb.append(" and policyCode like '" + bean.getPolicyCode() + "%'");

			if (bean.getDataType() != null && bean.getDataType().length() > 0)
				sb.append(" and dataType like '" + bean.getDataType() + "%'");

			if (bean.getArchiveAfterDays() > 0)
				sb.append(" and archiveAfterDays = " + bean.getArchiveAfterDays());

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
			bean = new PolicyBean();

			bean.setPolicyId(rs.getLong(1));
			bean.setPolicyCode(rs.getString(2));
			bean.setDataType(rs.getString(3));
			bean.setArchiveAfterDays(rs.getInt(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}
	
	public PolicyBean findByCode(String policyCode) throws Exception {
		
		PolicyBean bean = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
		
		PreparedStatement pstm = conn.prepareStatement("select * from archivalPolicy where policyCode = ?");
		pstm.setString(1, policyCode);
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			bean = new PolicyBean();
			
			bean.setPolicyId(rs.getLong(1));
			bean.setPolicyCode(rs.getString(2));
			bean.setDataType(rs.getString(3));
			bean.setArchiveAfterDays(rs.getInt(4));
			bean.setStatus(rs.getString(5));
		}
	
		return bean;
	}

}
