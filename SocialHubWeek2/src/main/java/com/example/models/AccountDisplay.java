package com.example.models;

public class AccountDisplay {
		
		private String username;
		private int accountId;
		private int customerId;
		private int transactionId;
		private double balance;
		
		public AccountDisplay() {
			super();
		}

		public AccountDisplay(String username, int accountId, int customerId, int transactionId, double balance) {
			super();
			this.username = username;
			this.accountId = accountId;
			this.customerId = customerId;
			this.transactionId = transactionId;
			this.balance = balance;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
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

		public int getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}

		public Double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		@Override
		public String toString() {
			return "AccountDisplay [username=" + username + ", accountId=" + accountId + ", customerId=" + customerId + ", transactionId="
					+ transactionId + ", balance=" + balance + "]";
		}

}
