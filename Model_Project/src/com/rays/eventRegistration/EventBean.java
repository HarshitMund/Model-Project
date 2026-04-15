package com.rays.eventRegistration;

import java.util.Date;

public class EventBean {

	private int id;
	private String praticipant_name;
	private String event_name;
	private String email;
	private Date registration_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPraticipant_name() {
		return praticipant_name;
	}

	public void setPraticipant_name(String praticipant_name) {
		this.praticipant_name = praticipant_name;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

}
