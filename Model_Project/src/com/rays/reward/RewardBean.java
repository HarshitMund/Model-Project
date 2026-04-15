package com.rays.reward;

public class RewardBean {

	private long rawardId;
	private String rewardCode;
	private String rewardName;
	private int pointsRequired;
	private String status;

	public long getRawardId() {
		return rawardId;
	}

	public void setRawardId(long rawardId) {
		this.rawardId = rawardId;
	}

	public String getRewardCode() {
		return rewardCode;
	}

	public void setRewardCode(String rewardCode) {
		this.rewardCode = rewardCode;
	}

	public String getRewardName() {
		return rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
