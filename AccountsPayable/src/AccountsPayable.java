
import java.util.Scanner;

public class AccountsPayable {

	public static void main(String[] args) {

		int input = 0;
		String fName;
		String lName;
		String ssn;
		int i;

		Employee[] payableEmployees = new Employee[6];
		Scanner scnr = new Scanner(System.in);
			

		for (i = 0; i < payableEmployees.length; i++) {
			//user must enter number within bounds
			while (input < 1 || input > 4) {
			System.out.println(
					"What type of Employee?\n1: Salaried Employee\n2: Hourly Employee\n3: Commission Employee\n4: BasePlus Commission Employee");
			input = scnr.nextInt();	
			}
			//clears input
			scnr.nextLine();
			
			System.out.println("Employee's First Name: ");
			fName = scnr.nextLine();
			System.out.println("Employee's Last Name: ");
			lName = scnr.nextLine();
			System.out.println("Employee's Social Security Number: ");
			ssn = scnr.nextLine();

			// Salaried Employee
			if (input == 1) {
				System.out.println("Enter Employee's weekly salary: ");
				double sal = scnr.nextDouble();
				payableEmployees[i] = new SalariedEmployee(fName, lName, ssn, sal);
			// Clears input and resets
				input = 0;
				scnr.nextLine();

			}
			// Hourly Employee
			else if (input == 2) {
				System.out.println("Enter Employee's hourly wage: ");
				double wage = scnr.nextDouble();
				System.out.println("Enter Employee's hours: ");
				double hours = scnr.nextDouble();
				payableEmployees[i] = new HourlyEmployee(fName, lName, ssn, wage, hours);
			// Clears input and resets
				input = 0;
				scnr.nextLine();
			}
			// Commission Employee
			else if (input == 3) {
				System.out.println("Enter Employee's gross sales: ");
				double grSales = scnr.nextDouble();
				System.out.println("Enter Employee's commission rate: ");
				double comRate = scnr.nextDouble();
				payableEmployees[i] = new CommissionEmployee(fName, lName, ssn, grSales, comRate);
			// Clears input and resets
				input = 0;
				scnr.nextLine();
			}
			// BasePlus Commission Employee
			else if (input == 4) {
				System.out.println("Enter Employee's gross sales:");
				double grSales = scnr.nextDouble();
				System.out.println("Enter Employee's commission rate: ");
				double comRate = scnr.nextDouble();
				System.out.println("Enter Employee's base pay: ");
				double basePay = scnr.nextDouble();
				payableEmployees[i] = new BasePlusCommissionEmployee(fName, lName, ssn, grSales, comRate,
						basePay);
			// Clears input and resets
				input = 0;
				scnr.nextLine();
			}
		}
		printToString(payableEmployees[1]);
		printNoString(payableEmployees[2]);
		printNameAndPay(payableEmployees);
		
		System.out.println("Adding 10% to each Base Plus Commission Employee's base pay.");
		for (i = 0; i < payableEmployees.length; i++) {
			if (payableEmployees[i] instanceof BasePlusCommissionEmployee) {
				double oldBase = ((BasePlusCommissionEmployee) payableEmployees[i]).getBasePay();
				((BasePlusCommissionEmployee) payableEmployees[i]).setBasePay(oldBase*1.1);
			}
		}
		printNameAndPay(payableEmployees);
	}


		// Methods that prints employee details
		public static void printToString(Employee emp) {
			System.out.println(emp.toString());
		}

		public static void printNoString(Employee emp) {
			System.out.println("Name: " + emp.getfName() + " " + emp.getlName() + "\nSocial Security Number: "
					+ emp.getSsn() + "\nPayment Amount: " + emp.getPaymentAmount());

		}
		
		public static void printNameAndPay(Employee[] payableEmployees) {
			for (int i = 0; i < payableEmployees.length; i++) {
				System.out.println(payableEmployees[i].getfName() + " " + payableEmployees[i].getlName() + " makes: $"
						+ payableEmployees[i].getPaymentAmount());
			}
		}
}
