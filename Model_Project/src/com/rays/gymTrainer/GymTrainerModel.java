package com.rays.gymTrainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GymTrainerModel {

	public long add(GymTrainierBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into gymTrainer values (?, ?, ?, ?)");
			pstm.setLong(1, bean.getTrainerId());
			pstm.setString(2, bean.getTrainerName());
			pstm.setString(3, bean.getSpecialization());
			pstm.setDouble(4, bean.getSalary());

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

		return bean.getTrainerId();
	}

	public void update(GymTrainierBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update gymTrainer set trainerName = ?, specialization = ?, salary = ? where trainerId = ?");
			pstm.setString(1, bean.getTrainerName());
			pstm.setString(2, bean.getSpecialization());
			pstm.setDouble(3, bean.getSalary());
			pstm.setLong(4, bean.getTrainerId());

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

	public void delete(GymTrainierBean bean) throws Exception {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from gymTrainer where trainerId = ?");
			pstm.setLong(1, bean.getTrainerId());

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

	public List<GymTrainierBean> search(GymTrainierBean bean, int pageNo, int pageSize) throws Exception {

		List<GymTrainierBean> list = new ArrayList<GymTrainierBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

		StringBuffer sb = new StringBuffer("select * from gymTrainer where 1 = 1");

		if (bean != null) {
			if (bean.getTrainerName() != null && bean.getTrainerName().length() > 0)
				sb.append(" and trainerName like '" + bean.getTrainerName() + "%'");

			if (bean.getSpecialization() != null && bean.getSpecialization().length() > 0)
				sb.append(" and specialization like '" + bean.getSpecialization() + "%'");

			if (bean.getSalary() > 0)
				sb.append(" and salary = " + bean.getSalary());

			if (bean.getTrainerId() > 0)
				sb.append(" and trainerId = " + bean.getTrainerId());
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sb.append(" limit " + pageNo + ", " + pageSize);
		}

		PreparedStatement pstm = conn.prepareStatement(sb.toString());

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			bean = new GymTrainierBean();

			bean.setTrainerId(rs.getLong(1));
			bean.setTrainerName(rs.getString(2));
			bean.setSpecialization(rs.getString(3));
			bean.setSalary(rs.getDouble(4));

			list.add(bean);
		}

		return list;
	}

}
