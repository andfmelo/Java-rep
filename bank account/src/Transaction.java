/**
* Program Name: Transaction.java
* Purpose: To generate a transaction report to be recorded.
* Coder: Andre Florentino Melo, Student ID 0922836
* Date: Feb. 12, 2020
*/

public class Transaction 
{
	//intance variables
	private String month;
	private int day;
	private  String transaction;
	private double amount;
	private double balance;

	//constructor
	//no arg constructor
	public Transaction()
	{
		month = "null";
		day = 0;
		transaction = "null";
		amount = 0.0;
		balance = 0.0;
	}
	
	//getters and setters

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	//methods
	//whats the return on this one, try to figure it out later.
	
	
	public String toString()
	{
		return month+"\t"+day+"\t"+transaction+":\t$"+amount+"\tbalance:\t$"+balance+"\n";
	}
	
}
