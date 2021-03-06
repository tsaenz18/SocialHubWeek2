package SocialHubDriver.java;

import java.util.*;

import com.example.models.User;

import com.example.dao.AccountDao;
import com.example.dao.AccountDaoDB;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.exceptions.InvalidCredentialsException;
import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;
import com.example.services.AccountService;
import com.example.services.UserService;

public class BankAppDriver {
	

	public static UserDao uDao = new UserDaoDB();
	public static AccountDao aDao = new AccountDaoDB();
	public static UserService uServ = new UserService(uDao);
	public static AccountService aServ = new AccountService(aDao);
	
	public static void main(String[] args) {
		
		
		Scanner in = new Scanner(System.in);
		
		//This will be used to control our loop
		boolean done = false;
		
		User u = null;
		
		while(!done) {
			if(u == null) {
				System.out.println("Login or Signup? Press 1 to Login, Press 2 to Signup");
				int choice = Integer.parseInt(in.nextLine());
				if(choice == 1) {
					System.out.print("Please enter your username: ");
					String username = in.nextLine();
					System.out.print("Please enter your password: ");
					String password = in.nextLine();
					try {
						u = uServ.signIn(username, password);
						System.out.println("Welcome " + u.getFirstName());
					} catch(Exception e) {
						System.out.println("Username or password was incorect. Goodbye");
						done = true;
					}
				} else {
					System.out.print("Please enter you first name: ");
					String first = in.nextLine();
					System.out.print("Please enter your last name: ");
					String last = in.nextLine();
					System.out.print("Please enter your email: ");
					String email = in.nextLine();
					System.out.print("Please enter a password: ");
					String password = in.nextLine();
					try {
						u = uServ.signUp(first, last, email, password);
						System.out.println("You may now log in with the username: " + u.getUsername());
					} catch (Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later");
						done = true;
					}
				}
			} else {
				System.out.println("To view balance press 1." +"\n"
						+ "To start an account press 2." +"\n"
						+ "To transfer funds press 3." + "\n"
						+ "To loggout or quit press 4.");
				int choice = Integer.parseInt(in.nextLine());
				//If the user chooses 1, we will show them the list of accounts
				if(choice == 1) {
					List<AccountDisplay> accounts = aServ.getAllAccounts();
					for(AccountDisplay account: accounts) {
						System.out.println(account.getUsername() + ":");
						System.out.println(account.getBalance());
						System.out.println();
					}	
					System.out.println("Are you finished? Press 1 for yes, press 2 for no");
					choice = Integer.parseInt(in.nextLine());
					done = (choice == 1) ? true : false;
		    	
				} else if (choice == 3){
					System.out.println("Please enter your amount below:");
					double amount = in.nextDouble();
					aServ.addAccount(u.getId(), u.getId(), amount);
					System.out.println("Funds trasfer. Are you finished? Press 1 for yes, press 2 for no");
					choice = Integer.parseInt(in.nextLine());
					done = (choice == 1) ? true : false;

				} else if (choice == 2) {
					System.out.println("Please enter the amount you'd like to deposit initially:");
					double amount = in.nextDouble();
					try {
						aServ.createNewAccount(u.getId(), u.getId(), amount);
						System.out.println("Your account number is:");
						done =true;
						
					} catch (Exception e) {
						System.out.println("Sorry, we could not process you request.");
						System.out.println("Please try again later.");
						done = true;}
				
				} else if (choice == 3) {
					System.out.println("Please enter the number of the account to deposit to: ");
					int receiver = in.nextInt();
					System.out.println("Please enter the amount you would like to transfer to account number "+ receiver +".");
					double amount = in.nextDouble();
					System.out.println(amount + "monies has been deposited into account number "+ receiver + ".");
				} else if (choice == 4) {
					done = true;
					
		}
		System.out.println("Goodbye :)");
		in.close();
	}
	}
	}
	}
	
			
