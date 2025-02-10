
public class HourlyEmployee extends Employee {
	//Data Members
	public double wage;
	public double hours;
	
	//Constructor
	public HourlyEmployee(String fName, String lName, String ssn, double wage, double hours) {
		super(fName, lName, ssn);
		this.wage = wage;
		this.hours = hours;
	}
	//Getters and Setters
	public double getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nPayment Amount: " + getPaymentAmount();
	}

	@Override
	public double getPaymentAmount() {
		
		return (wage*hours); //amountEmployeeIsPaid
	}
	
}
