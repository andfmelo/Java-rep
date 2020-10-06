/**
* Program Name: BankAccount.java
* Purpose: this program will implement a simulation of a bank account, using oop structure.
* Coder: Andre Florentino Melo, Student ID 0922836
* Date: Feb. 10, 2020
*/

public abstract class BankAccount
{
	
	//instance variables
	private String customerName;
	private String accountType;
	private String month;
	
	//constructors
	//no arg constructor
	public BankAccount()
	{
		customerName = "null";
		accountType ="null";
		month = "null";
	}
	
	//3 arg constructor
	public BankAccount(String customerName, String accountType, String month)
	{
		this.customerName = customerName;
		this.accountType = accountType;
		this.month = month;
	}

	//setters and getters
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	//methods


	/**
	* Method Name: generateAccountNumber()
	* Purpose: this method will generate a random account number for the user
	* Accepts: nothing
	* Returns: a string with a randomly generated part of the account number.
	* Date: Feb. 10, 2020
	**/
	public abstract String generateAccountNumber();
	
	/**
	* Method Name: deposit()
	* Purpose: this method will serve to simulate deposits into both of the available accounts.
	* Accepts: when overriden, accepts the amount to deposit (double) and the day of the deposit (int)
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public abstract void deposit(double depositAmount, int day);
	
	
	/**
	* Method Name: withdrawal()
	* Purpose: this method will serve to simulate withdrawals into both of the available accounts.
	* Accepts: when overriden, accepts the amount to withdrawal (double) and the day of the withdrawal (int)
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public abstract void withdrawal(double withdrawalAmount, int day);
	
	/**
	* Method Name: recordTransaction()
	* Purpose: this method will serve to record all transactions.
	* Accepts: accepts an int for the day, string for the type of transaction, and two doubles for amount and balance.
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public abstract void recordTransaction(int day, String transaction, double amount, double balance);
	
	/**
	* Method Name: recordTransaction()
	* Purpose: this method will serve to record the transactions of the month, printing the montlhy processes.
	* Accepts: accepts an int for the day, string for the type of transaction, and two doubles for amount and balance.
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public abstract void monthlyProcess(); 
	
	
	
	//toString()
	/**
	* Method Name: toString()
	* Purpose: override the default toString() into a more specialized toString() method.
	* Accepts: nothing.
	* Returns: void.
	* Date: Feb. 10, 2020
	**/
	public String toString()
	{
		return "*********************************************\n"+"Customer Name:\t\t"+customerName+"\nAccount Type:\t\t"+accountType+
				"\nCurrent Month:\t\t"+month+"\n"+"*********************************************\n";
	}

	

}
