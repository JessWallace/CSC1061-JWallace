
public class AccountTest {

	public static void main(String[] args) {
		CheckingAccount chk = new CheckingAccount(111, 2000, 200);
		chk.setAnnualInterestRate(0.5);
		chk.getMonthlyInterestRate();
		System.out.println(chk.toString());
		chk.withdraw(2100);
		System.out.println(chk.toString());
		chk.deposit(3000);
		System.out.println(chk.toString());

		SavingsAccount sav = new SavingsAccount(1100, 500, 200);
		sav.setAnnualInterestRate(1.0);
		sav.getMonthlyInterestRate();
		System.out.println(sav.toString());
		sav.withdraw(300);
		System.out.println(sav.toString());
		
		CheckingAccount chk2 = new CheckingAccount(2222, 1000, 200);
		chk2.setAnnualInterestRate(1.5);
		chk2.getMonthlyInterestRate();
		chk2.setName("George");
		chk2.deposit(30);
		chk2.deposit(40);
		chk2.deposit(50);
		chk2.withdraw(5);
		chk2.withdraw(4);
		chk2.withdraw(2);
		System.out.println(chk2.toString() + chk2.getTransaction());
	}

}
