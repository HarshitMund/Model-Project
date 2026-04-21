package com.rays.dataImportLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImportLogModel {

	public long nextPk() throws Exception {

		long pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		PreparedStatement pstm = conn.prepareStatement("select max(importLogId) from dataImportLog");

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			pk = rs.getLong(1);
		}

		return pk + 1;
	}

	public void add(ImportLogBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into dataImportLog values (?, ?, ?, ?, ?)");
			pstm.setLong(1, nextPk());
			pstm.setString(2, bean.getImportLogCode());
			pstm.setString(3, bean.getFileName());
			pstm.setString(4, bean.getImportedBy());
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

	public void update(ImportLogBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update dataImportLog set importLogCode = ?, fineName = ?, importedBy = ?, status = ? where importLogId = ?");
			pstm.setString(1, bean.getImportLogCode());
			pstm.setString(2, bean.getFileName());
			pstm.setString(3, bean.getImportedBy());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getImportLogId());

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

	public void delete(ImportLogBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from dataImportLog where importLogId = ?");
			pstm.setLong(1, bean.getImportLogId());

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

	public List<ImportLogBean> search(ImportLogBean bean, int pageNo, int pageSize) throws Exception {

		List<ImportLogBean> list = new ArrayList<ImportLogBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from dataImportLog where 1 = 1");

		if (bean != null) {
			if (bean.getImportLogId() > 0)
				sb.append(" and importLogId = " + bean.getImportLogId());

			if (bean.getImportLogCode() != null && bean.getImportLogCode().length() > 0)
				sb.append(" and importLogCode like '" + bean.getImportLogCode() + "%'");

			if (bean.getFileName() != null && bean.getFileName().length() > 0)
				sb.append(" and fileName like '" + bean.getFileName() + "%'");

			if (bean.getImportedBy() != null && bean.getImportedBy().length() > 0)
				sb.append(" and importedBy like '" + bean.getImportedBy() + "%'");

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
			bean = new ImportLogBean();

			bean.setImportLogId(rs.getLong(1));
			bean.setImportLogCode(rs.getString(2));
			bean.setFileName(rs.getString(3));
			bean.setImportedBy(rs.getString(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}

}
