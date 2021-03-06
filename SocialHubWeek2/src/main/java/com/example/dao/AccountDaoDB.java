package com.example.dao;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;
import com.example.utils.ConnectionUtil;

public class AccountDaoDB implements AccountDao{
	
	private ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	
	//We use callable statements to call stored procedures and functions from java
	@Override
	public void createAccount(Account a) {
		try {
			Connection con = conUtil.getConnection();
			//To use our functions/procedure we need to turn off autocommit
			con.setAutoCommit(false);
			String sql = "call create_account(?,?)";
			CallableStatement cs = con.prepareCall(sql);
			
			cs.setInt(1, a.getCustomerId());
			cs.setDouble(2, a.getAccountBalance());
	
			
			cs.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public Account checkBalance(int acc_id) {
		Account accounts = new Account();
		
		 try {
		Connection con = conUtil.getConnection();
		String sql = "SELECT SUM (acc_balance) AS total\r\n"
                + "FROM accounts\r\n"
                + "WHERE acc_id ='" + acc_id + "'";
		
		PreparedStatement ps = con.prepareCall(sql);
		
		Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(sql);

		
		while(rs.next()) {
			accounts.setAccountBalance(rs.getInt(1));
		}
		return accounts;
		
	} catch(SQLException e) {
		e.printStackTrace();
		
	} return null;
	}

	@Override
	public List<AccountDisplay> getAllAccounts() {
		
		List<AccountDisplay> aList = new ArrayList<AccountDisplay>();
		
		try {
			Connection con = conUtil.getConnection();
			con.setAutoCommit(false);
			//Use this syntax to call a stored function
			String sql = "{?=call get_all_accounts()}";
			
			CallableStatement cs = con.prepareCall(sql);
			
			cs.registerOutParameter(1, Types.OTHER);
			
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				AccountDisplay account = new AccountDisplay(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));
				aList.add(account);
			}
			
			con.setAutoCommit(true);
			return aList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getUsersAccounts(User u) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection con = conUtil.getConnection();
			con.setAutoCommit(false);
			String sql = "{?=call get_user_accounts(?)}";
			
			CallableStatement cs = con.prepareCall(sql);
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, u.getId());
			
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				Account a = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4));
				accounts.add(a);
			}
			
			u.setAccounts(accounts);
			
			con.setAutoCommit(true);
			return u;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
