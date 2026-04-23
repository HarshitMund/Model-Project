package com.rays.broadcast;

public class BroadcastBean {

	private long broadcastId;
	private String broadcastCode;
	private String message;
	private String sentBy;
	private String status;

	public long getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(long broadcastId) {
		this.broadcastId = broadcastId;
	}

	public String getBroadcastCode() {
		return broadcastCode;
	}

	public void setBroadcastCode(String broadcastCode) {
		this.broadcastCode = broadcastCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
