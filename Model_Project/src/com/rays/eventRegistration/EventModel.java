package com.rays.eventRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EventModel {

	public int add(EventBean bean) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("insert into event_registration values (?, ?, ?, ?, ?)");

			pstm.setInt(1, bean.getId());
			pstm.setString(2, bean.getPraticipant_name());
			pstm.setString(3, bean.getEvent_name());
			pstm.setString(4, bean.getEmail());
			pstm.setDate(5, new Date(bean.getRegistration_date().getTime()));

			int i = pstm.executeUpdate();

			pstm.close();
			conn.commit();
			System.out.println(i + " rows affected(Rows inserted)");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return bean.getId();
	}

	public void update(EventBean bean) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement(
					"update event_registration set participant_name = ?, event_name = ?, email = ?, registration_date = ? where id = ?");

			pstm.setString(1, bean.getPraticipant_name());
			pstm.setString(2, bean.getEvent_name());
			pstm.setString(3, bean.getEmail());
			pstm.setDate(4, new Date(bean.getRegistration_date().getTime()));
			pstm.setInt(5, bean.getId());

			int i = pstm.executeUpdate();

			pstm.close();
			conn.commit();
			System.out.println(i + " rows affected(Rows updated)");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public void delete(EventBean bean) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement pstm = conn.prepareStatement("delete from event_registration where id = ?");

			pstm.setInt(1, bean.getId());

			int i = pstm.executeUpdate();

			pstm.close();
			conn.commit();
			System.out.println(i + " rows affected(Rows deleted)");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

	public EventBean findByPK(int id) throws Exception {

		EventBean bean = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");

			PreparedStatement pstm = conn.prepareStatement("select * from event_registration where id = ?");
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				bean = new EventBean();
				bean.setId(rs.getInt(1));
				bean.setPraticipant_name(rs.getString(2));
				bean.setEvent_name(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setRegistration_date(rs.getDate(5));
			}

			pstm.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return bean;
	}

}
