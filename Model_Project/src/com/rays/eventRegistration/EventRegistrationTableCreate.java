package com.rays.eventRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EventRegistrationTableCreate {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelproject", "root", "root");
			conn.setAutoCommit(false);

			Statement stmt = conn.createStatement();

			boolean pass = stmt
					.execute("create table event_registration(id int primary key, participant_name varchar(45), event_name varchar(45), email varchar(45), registration_date date)");
			
			stmt.close();
			conn.commit();
			System.out.println("Event Registration table created.");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}

}
