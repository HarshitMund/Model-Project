package com.rays.build;

public class BuildBean {

	private long buildId;
	private String buildCode;
	private String buildVersion;
	private String triggeredBy;
	private String status;

	public long getBuildId() {
		return this.buildId;
	}

	public void setBuildId(long buildId) {
		this.buildId = buildId;
	}

	public String getBuildCode() {
		return buildCode;
	}

	public void setBuildCode(String buildCode) {
		this.buildCode = buildCode;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getTriggeredBy() {
		return triggeredBy;
	}

	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
