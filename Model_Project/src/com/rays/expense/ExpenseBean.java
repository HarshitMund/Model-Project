package com.rays.expense;

public class ExpenseBean {

	private long expenxeId;
	private String expenseCode;
	private double amount;
	private String category;
	private String status;

	public long getExpenxeId() {
		return expenxeId;
	}

	public void setExpenxeId(long expenxeId) {
		this.expenxeId = expenxeId;
	}

	public String getExpenseCode() {
		return expenseCode;
	}

	public void setExpenseCode(String expenseCode) {
		this.expenseCode = expenseCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
