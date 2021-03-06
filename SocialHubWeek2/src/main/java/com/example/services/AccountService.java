package com.example.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.FileIO;
import com.example.dao.AccountDao;
import com.example.logging.Logging;
import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;

public class AccountService {
	
		private AccountDao aDao;
	  	private double balance;
	  	private Account a;
		
		public AccountService(AccountDao a) {
			this.aDao = a;}
		
	public Account createNewAccount(int user_id, int acc_id, double deposit_0) throws SQLException {
		Account new_account = new Account(user_id, acc_id, deposit_0);
		aDao.createAccount(a);
		Logging.logger.info("aServ: New account # " + a.getTransUserId() + " has been created.");

        return a;
	}
	//might remove transactionId
	public void addAccount(int customerId, int accountId, double balance) {
		Account a = new Account(customerId, accountId, balance);
		aDao.createAccount(a);
	}
	
	public List<AccountDisplay> getAllAccounts(){
		return aDao.getAllAccounts();
	}
	
	public User loadUserPosts(User u) {
		return aDao.getUsersAccounts(u);
	}

}