package com.rays.subscriptionUsage;

public class SubscriptionBean {

	private long usageId;
	private String usageCode;
	private String userName;
	private int usageCount;
	private String status;

	public long getUsageId() {
		return usageId;
	}

	public void setUsageId(long usageId) {
		this.usageId = usageId;
	}

	public String getUsageCode() {
		return usageCode;
	}

	public void setUsageCode(String usageCode) {
		this.usageCode = usageCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUsageCount() {
		return usageCount;
	}

	public void setUsageCount(int usageCount) {
		this.usageCount = usageCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
