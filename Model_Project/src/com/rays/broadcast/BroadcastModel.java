package com.rays.broadcast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BroadcastModel {

	public long nextPk() throws Exception {

		long pk = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
		PreparedStatement pstm = conn.prepareStatement("select max(broadcastId) from broadcast");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		rs.close();
		pstm.close();
		conn.close();
		return pk + 1;
	}

	public long add(BroadcastBean bean) throws Exception {

		Connection conn = null;
		long pk = nextPk();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement("insert into broadcast values (?, ?, ?, ?, ?)");
			pstm.setLong(1, pk);
			pstm.setString(2, bean.getBroadcastCode());
			pstm.setString(3, bean.getMessage());
			pstm.setString(4, bean.getSentBy());
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
		return pk;
	}

	public void update(BroadcastBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement(
					"update broadcast set broadcastCode = ?, message = ?, sentBy = ?, status = ? where broadcastId = ?");
			pstm.setString(1, bean.getBroadcastCode());
			pstm.setString(2, bean.getMessage());
			pstm.setString(3, bean.getSentBy());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getBroadcastId());
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

	public void delete(BroadcastBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn.prepareStatement("delete from broadcast where broadcastId = ?");
			pstm.setLong(1, bean.getBroadcastId());
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

	public List<BroadcastBean> search(BroadcastBean bean, int pageNo, int pageSize) throws Exception {

		List<BroadcastBean> list = new ArrayList<BroadcastBean>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

			StringBuffer sb = new StringBuffer("select * from broadcast where 1 = 1");

			if (bean != null) {
				if (bean.getBroadcastId() > 0)
					sb.append(" and broadcastId = " + bean.getBroadcastId());

				if (bean.getBroadcastCode() != null && bean.getBroadcastCode().length() > 0)
					sb.append(" and broadcastCode like '" + bean.getBroadcastCode());

				if (bean.getMessage() != null && bean.getMessage().length() > 0)
					sb.append(" and message like '" + bean.getMessage() + "%'");

				if (bean.getSentBy() != null && bean.getSentBy().length() > 0)
					sb.append(" and sentBy like '" + bean.getSentBy() + "%'");

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
				bean = new BroadcastBean();
				bean.setBroadcastId(rs.getLong(1));
				bean.setBroadcastCode(rs.getString(2));
				bean.setMessage(rs.getString(3));
				bean.setSentBy(rs.getString(4));
				bean.setStatus(rs.getString(5));
				list.add(bean);
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return list;
	}

}
