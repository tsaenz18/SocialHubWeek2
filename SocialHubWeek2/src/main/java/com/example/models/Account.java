package com.example.models;

public class Account {
	
	private int accountId;
	private int customerId;
	private int transUserId;
	private double accountBalance;
	
	public Account() {
		
	}
	public Account(int id, int customerId, int transUserId, double balance) {
		this.accountId = id;
		this.customerId = customerId;
		this.transUserId = transUserId;
		this.accountBalance = balance;
	}
	
	public Account(int customerId, int transUserId, double balance) {
		this.customerId = customerId;
		this.transUserId = transUserId;
		this.accountBalance = balance;
	}
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getTransUserId() {
		return transUserId;
	}

	public void setTransUserId(int transUserId) {
		this.transUserId = transUserId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Post [accountId=" + accountId + ", customerId=" + customerId + ", transUserId=" + transUserId + ", accountBalace="
				+ accountBalance + "]";
	}

}


