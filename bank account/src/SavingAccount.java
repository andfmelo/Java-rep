/**
* Program Name: SavingAccount.java
* Purpose: Simulate a personal savings account.
* Coder: Andre Florentino Melo, Student ID 0922836
* Date: Feb. 17, 2020
*/

import java.util.ArrayList;
import java.util.Calendar;

public class SavingAccount extends BankAccount implements InterestPayable
{

	//instance variables
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	private final double INT_RATE = 0.03;
	ArrayList<Transaction> record = new ArrayList<Transaction>();
	
	//constructors
	//no-arg constructor
	public SavingAccount()
	{
		super();
		setCustomerName("null");
		setAccountType("null");
		accountNumber = "null";
		accountActive = false;
		numberWithdrawals = 0;
		numberDeposits = 0;
		balance = 0.0;
	}
	
	//3-arg constructor
	public SavingAccount(String customerName, String month, double balance)
	{
		setCustomerName(customerName);
		setMonth(month);
		this.balance = balance;
		setAccountType("Saving");
		accountNumber = "002-623490-"+generateAccountNumber()+"-575";
		accountActive = isAccountActive();
	}
	//getters and setters

	public String getAccountNumber() {
		return accountNumber;
	}

	public int getNumberWithdrawals() {
		return numberWithdrawals;
	}

	public int getNumberDeposits() {
		return numberDeposits;
	}

	public double getBalance() {
		return balance;
	}
	
	//methods
	public boolean isAccountActive()
	{
		if(balance < 25.0)
		{
			return false;
		}

		return true;
	}
	
	public String generateAccountNumber()
	{
		int accountNumberArray[] = new int[6];
		
		for(int i = 0; i<accountNumberArray.length; i++)
		{
			accountNumberArray[i] = (int)(Math.random()*9);
		}
		
		StringBuilder stringbuilder = new StringBuilder();
		
		for (int j = 0; j < accountNumberArray.length; j++) {
		    stringbuilder.append(accountNumberArray[j]); 
		}
		
		String accountNumberArrayString = stringbuilder.toString();
		
		return accountNumberArrayString;
		
	}
	
	public void deposit(double depositAmount, int day)
	{
		//update account balance
		this.balance = this.balance + depositAmount;
		//update number of deposits
		numberDeposits = numberDeposits+1;
		//update account active -> calling isAccountActive() method.
		accountActive = isAccountActive();
		//update type of account
		String transactionMessage = "deposit";
		//record everything
		recordTransaction(day, transactionMessage, depositAmount, balance);
		
	}
	
	public void withdrawal(double withdrawalAmount, int day)
	{
		String transactionMessage = "";
		
		if((balance - withdrawalAmount) > 0 && (accountActive == true))
		{
			balance = balance - withdrawalAmount;
			transactionMessage ="Withdrawal";
			numberWithdrawals = numberWithdrawals+1;
			accountActive = isAccountActive();
			
			recordTransaction(day, transactionMessage, withdrawalAmount, balance);
		}
		else if((balance - withdrawalAmount) <0)
		{
			transactionMessage = "Transaction cancelled. Insufficient founds";
			recordTransaction(day, transactionMessage, withdrawalAmount, balance);
		}
		else if(accountActive == false)
		{
			transactionMessage = "Transaction cancelled. Account is inactive";
			recordTransaction(day, transactionMessage, withdrawalAmount, balance);
		}
		
	}
	
	public void calcInterest()
	//something is not working properly.
	{
		String transactionMessage = "Interest";
		int posBalance = 0;
		int negBalance = 0;
		double bonusRate = 0.0;
		
		//get the actual input month
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 2); //0 for january and so on...
	    int lastDay = calendar.getActualMaximum(Calendar.DATE);
	    double interestRate = balance * (double)INT_RATE/12;
	    
	    
		if(balance >= 25)
		{
			
			for(Transaction balanceMaintained : record)	
			{
				if(balanceMaintained.getBalance() >= 2000)
				{
				posBalance++;
				}
				else if(balanceMaintained.getBalance() < 2000)
				{
				negBalance++;
				}
		
			}//end for
		
		}//end if
		
		if(posBalance != 0 && negBalance == 0)
		{
			bonusRate = INT_RATE + 0.0075;
			interestRate = balance * (double)bonusRate/12;
		}
		else
		{
			interestRate = balance * (double)INT_RATE/12;
		}
			
		balance = balance + interestRate;
		
		
		
		recordTransaction(lastDay, transactionMessage, interestRate, balance);
	}
	
	public void recordTransaction(int day, String transaction, double amount, double balance)
	{
		Transaction trans = new Transaction();
		
		trans.setMonth(getMonth());
		trans.setDay(day);
		trans.setTransaction(transaction);
		trans.setAmount(amount);
		trans.setBalance(balance);
		
		record.add(trans);
	}
	
	public void printTransaction()
	{
		System.out.print("*********************************************\n"+"Transaction record for the Month of "+getMonth()+"\n*********************************************\n");
		
		for(Transaction transOperation:record)
		{
			System.out.printf("%s\t %d\t %s%s\t %s%-8.2f\t %s\t %s%.2f\n", transOperation.getMonth(), transOperation.getDay(), 
					transOperation.getTransaction(),":","$", transOperation.getAmount(),
					"Balance:","$",transOperation.getBalance());
		}
		
	}
	
	
	public void monthlyProcess() 
	{
		String transactionMessage = "Service Fee";
		//get the actual input month
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 2); //0 for january and so on...
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		
		calcInterest();
		recordTransaction(lastDay, transactionMessage, 0, balance);
		printTransaction();
		accountActive = isAccountActive();
		
	}
		
	public String toString()
	{
		return super.toString()+"Account number:\t\t\t"+accountNumber+"\nNumber of Withdrawals:\t\t"+numberWithdrawals+"\nNumber of Deposits:\t\t"+numberDeposits+
				"\nBalance:\t\t\t$"+String.format("%.2f ", balance)+"\nAccount Active:\t\t\t"+accountActive+"\nAnnual interest rate:\t\t"+(INT_RATE*100)+"%";
		//balance is printing a double with a lot of decimal places here.
	}

	

}//end of public class SavingAccount 
