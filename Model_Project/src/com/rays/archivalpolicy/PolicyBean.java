package com.rays.archivalpolicy;

public class PolicyBean {

	private long policyId;
	private String policyCode;
	private String dataType;
	private int archiveAfterDays;
	private String status;

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getArchiveAfterDays() {
		return archiveAfterDays;
	}

	public void setArchiveAfterDays(int archiveAfterDays) {
		this.archiveAfterDays = archiveAfterDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
