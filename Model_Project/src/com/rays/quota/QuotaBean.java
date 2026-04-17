package com.rays.quota;

public class QuotaBean {

	private long quotaId;
	private String quotaCode;
	private String userName;
	private int limitValue;
	private String status;

	public long getQuotaId() {
		return quotaId;
	}

	public void setQuotaId(long quotaId) {
		this.quotaId = quotaId;
	}

	public String getQuotaCode() {
		return quotaCode;
	}

	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLimitValue() {
		return limitValue;
	}

	public void setLimitValue(int limitValue) {
		this.limitValue = limitValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
