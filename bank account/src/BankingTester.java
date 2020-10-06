/**
* Program Name: BankingTester.java
* Purpose: The purpose of this program is to test our bank account program, using it's deposits and withdrawals options, as well as record
* 		   and print these transactions on the screen.
* Coder: Andre Florentino Melo, Student ID 0922836
* Date: Feb. 12, 2020
*/

public class BankingTester {

	
	public static void main(String[] args) {
		
		BankAccount acc1 = new PersonalChequingAccount("Janice Joplin", "March", 2345);
		BankAccount acc2 = new SavingAccount("Elvis Presley", "March", 6100);
			
		//For account 1
		//print general info
		System.out.println(acc1);

		//add values to the personal chequing account
		acc1.deposit(25.98, 5);
		acc1.withdrawal(1300, 6);
		acc1.withdrawal(1700, 10);
		acc1.deposit(1050, 11);
		acc1.withdrawal(1700, 11);
		acc1.deposit(25.98, 25);
		acc1.withdrawal(400, 26);
		acc1.withdrawal(27, 28);
		acc1.withdrawal(7.50, 28);
		
		//print information about transactions
		acc1.monthlyProcess();
		
		//print updated general info for account 1
		System.out.println(acc1);
		System.out.println("\n");
		
		
		//now for account 2
		//print general info
		System.out.println(acc2);
		
		//add values to the savings account
		acc2.deposit(500, 3);
		acc2.withdrawal(1000, 6);
		acc2.deposit(250, 15);
		acc2.withdrawal(3000, 21);
		acc2.withdrawal(825, 28);
		acc2.deposit(250, 29);
		
		//print information about transactions
		acc2.monthlyProcess();
		
		//print updated general info for account 2
		System.out.println(acc2);
	
	}

}
