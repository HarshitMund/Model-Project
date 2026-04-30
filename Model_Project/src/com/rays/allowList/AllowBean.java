package com.rays.allowList;

public class AllowBean {

	private long allowId;
	private String allowCode;
	private String allowName;
	private String source;
	private String status;

	public long getAllowId() {
		return allowId;
	}

	public void setAllowId(long allowId) {
		this.allowId = allowId;
	}

	public String getAllowCode() {
		return allowCode;
	}

	public void setAllowCode(String allowCode) {
		this.allowCode = allowCode;
	}

	public String getAllowName() {
		return allowName;
	}

	public void setAllowName(String allowName) {
		this.allowName = allowName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
