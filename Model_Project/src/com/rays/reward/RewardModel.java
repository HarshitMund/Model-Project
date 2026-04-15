package com.rays.reward;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RewardModel {

	public long add(RewardBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into reward values (?, ?, ?, ?, ?)");
			pstm.setLong(1, bean.getRawardId());
			pstm.setString(2, bean.getRewardCode());
			pstm.setString(3, bean.getRewardName());
			pstm.setInt(4, bean.getPointsRequired());
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

		return bean.getRawardId();
	}

	public void update(RewardBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update reward set rewardCode = ?, rewardName = ?, pointsRequired = ?, status = ? where rewardId = ?");
			pstm.setString(1, bean.getRewardCode());
			pstm.setString(2, bean.getRewardName());
			pstm.setInt(3, bean.getPointsRequired());
			pstm.setString(4, bean.getStatus());
			pstm.setLong(5, bean.getRawardId());

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

	public void delete(RewardBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from reward where rewardId = ?");
			pstm.setLong(1, bean.getRawardId());

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

	public List<RewardBean> search(RewardBean bean, int pageNo, int pageSize) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from reward where 1 = 1");

		if (bean != null) {
			if (bean.getRawardId() > 0)
				sb.append(" and rewardId = " + bean.getRawardId());

			if (bean.getRewardCode() != null && bean.getRewardCode().length() > 0)
				sb.append(" and rewardCode like '" + bean.getRewardCode() + "%'");

			if (bean.getRewardName() != null && bean.getRewardName().length() > 0)
				sb.append(" and rewardName like '" + bean.getRewardName() + "%'");

			if (bean.getPointsRequired() > 0)
				sb.append(" and pointsRequired = " + bean.getPointsRequired());

			if (bean.getStatus() != null && bean.getStatus().length() > 0)
				sb.append(" and status like '" + bean.getStatus() + "%'");
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		PreparedStatement pstm = conn.prepareStatement(sb.toString());

		ResultSet rs = pstm.executeQuery();

		List<RewardBean> list = new ArrayList<RewardBean>();

		while (rs.next()) {
			bean = new RewardBean();

			bean.setRawardId(rs.getLong(1));
			bean.setRewardCode(rs.getString(2));
			bean.setRewardName(rs.getString(3));
			bean.setPointsRequired(rs.getInt(4));
			bean.setStatus(rs.getString(5));

			list.add(bean);
		}

		return list;
	}

}
