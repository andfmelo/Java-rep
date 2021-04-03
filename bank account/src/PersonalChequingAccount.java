/**
* Program Name:  PersonalChequingAccount.java
* Purpose: Simulate a personal chequing account.
* Coder: Andre Florentino Melo
* Date: Feb. 10, 2020
*/

import java.util.ArrayList;
import java.util.Calendar;

public class PersonalChequingAccount extends BankAccount implements InterestPayable
{
	
	//instance variables
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	private final static double INT_RATE = 0.025;
	private final static double SERVICE_FEE = 0.85;
	
	private ArrayList<Transaction> record = new ArrayList<Transaction>();
	
	//constructors
	//no arg constructor
	
	public PersonalChequingAccount()
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
	
	public PersonalChequingAccount(String customerName, String month, double balance)
	{
		super();
		setCustomerName(customerName);
		//setting objects month here one time for all the iterations, later I'll check if it's necessary to add the month in every input.
		setMonth(month);
		setAccountType("Personal Chequing");
		this.balance = balance;
		accountNumber = "002-623490-"+generateAccountNumber()+"-550";
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

	public static double getINT_RATE() {
		return INT_RATE;
	}

	public static double getSERVICE_FEE() {
		return SERVICE_FEE;
	}
	
	
	//methods
	
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
		this.numberDeposits = this.numberDeposits+1;
		//update account active -> calling isAccountActive() method.
		accountActive = isAccountActive();
		//update type of account		
		String transactionMessage = "deposit";
		
		//record transaction for each deposit
		recordTransaction(day, transactionMessage , depositAmount, balance);
		
	}
	public void withdrawal(double withdrawalAmount, int day)
	{
		String transactionMessage = "";
		
		if((balance - withdrawalAmount) > 0 && accountActive == true)
		{
			transactionMessage = "Withdrawal";
			balance = balance - withdrawalAmount;
			numberWithdrawals = numberWithdrawals+1;
			accountActive = isAccountActive();
			
			recordTransaction(day, transactionMessage, withdrawalAmount, balance);
		}
		else if((balance - withdrawalAmount) < 0 )
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
	
	public void recordTransaction(int day, String transaction, double amount, double balance)
	{
		
		Transaction trans = new Transaction();
		
		trans.setMonth(getMonth());
		trans.setAmount(amount);
		trans.setBalance(balance);
		trans.setDay(day);
		trans.setTransaction(transaction);
				
		record.add(trans);
		
			
	}
	/**
	* Method Name: printTransaction()
	* Purpose: this method prints the monthly transactions for the chequing account
	* Accepts: nothing.
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public void printTransaction()
	{
		System.out.print("*********************************************\n"+"Transaction record for the Month of "+getMonth()+"\n*********************************************\n");
		for(Transaction transOperation: record) 
		{
			System.out.printf("%s\t %d\t %s%s\t %s%-8.2f\t %s\t %s%.2f\n", transOperation.getMonth(), transOperation.getDay(), 
					transOperation.getTransaction(),":","$", transOperation.getAmount(),
					"Balance:","$",transOperation.getBalance());
		}
	
	}
	
	/**
	* Method Name: isAccountActive()
	* Purpose: to check, depending on the balance, if an account is active or not.
	* Accepts: nothing
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public boolean isAccountActive()
	{
		if(balance < 25.00)
		{
			return false;
		}
		
		return true;
	}
	
	public String toString()
	{
		return super.toString()+"Account number:\t\t\t"+accountNumber+"\nNumber of Withdrawals:\t\t"+numberWithdrawals+"\nNumber of Deposits:\t\t"+numberDeposits+
				"\nBalance:\t\t\t$"+String.format("%.2f ", balance)+"\nAccount Active:\t\t\t"+accountActive+"\nAnnual interest rate:\t\t"+(INT_RATE*100)+"%"+"\nMonthly Service Fee:\t\t$"+SERVICE_FEE;
		//balance is printing a double with a lot of decimal places here.
	}

	
	public void calcInterest() 
	{
		
		String transactionMessage = "Interest";
		double interestRate = 0.0;
		
		//get the actual input month
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 2); //0 for january and so on...
	    int lastDay = calendar.getActualMaximum(Calendar.DATE);
		
		if(balance >= 1000)
		{
			interestRate = balance * (double)INT_RATE/12;
			balance = balance + interestRate;
		}
		
		recordTransaction(lastDay, transactionMessage, interestRate, balance);
		
		
	}

	
	public void monthlyProcess() 
	{
		String transactionMessage = "Service Fee";
		//get the actual input month
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 2); //0 for january and so on...
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		
		double serviceFee = 0.0;
		
		if(numberWithdrawals > 4)
		{
			serviceFee = numberWithdrawals * SERVICE_FEE;
			balance = balance + serviceFee;
		}
		calcInterest();
		recordTransaction(lastDay, transactionMessage, serviceFee, balance);
		printTransaction();
		accountActive = isAccountActive();
	}
	
	
}
