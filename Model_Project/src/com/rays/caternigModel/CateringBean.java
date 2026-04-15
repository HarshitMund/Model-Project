package com.rays.caternigModel;

public class CateringBean {

	private long cateringId;
	private String vendorName;
	private String menuType;
	private double cost;

	public long getCateringId() {
		return cateringId;
	}

	public void setCateringId(long cateringId) {
		this.cateringId = cateringId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
