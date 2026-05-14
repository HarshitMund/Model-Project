package com.rays.bean;

public class GamingBean {

	private long id;
	private String code;
	private String name;
	private double prizePool;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrizePool() {
		return prizePool;
	}

	public void setPrizePool(double prizePool) {
		this.prizePool = prizePool;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
