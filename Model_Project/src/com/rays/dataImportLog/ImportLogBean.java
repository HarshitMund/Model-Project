package com.rays.dataImportLog;

public class ImportLogBean {

	private long importLogId;
	private String importLogCode;
	private String fileName;
	private String importedBy;
	private String status;

	public long getImportLogId() {
		return importLogId;
	}

	public void setImportLogId(long importLogId) {
		this.importLogId = importLogId;
	}

	public String getImportLogCode() {
		return importLogCode;
	}

	public void setImportLogCode(String importLogCode) {
		this.importLogCode = importLogCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImportedBy() {
		return importedBy;
	}

	public void setImportedBy(String importedBy) {
		this.importedBy = importedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
