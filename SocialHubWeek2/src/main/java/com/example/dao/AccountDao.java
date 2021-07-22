package com.example.dao;


import java.util.List;

import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;

public interface AccountDao {
	
	public void createAccount(Account a);
	
	public List<AccountDisplay> getAllAccounts();
	
	public User getUsersAccounts(User u);

}
