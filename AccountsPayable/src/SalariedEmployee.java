
public class SalariedEmployee extends Employee {
	// Weekly Salary
	public double sal;

//Constructor
	public SalariedEmployee(String fName, String lName, String ssn, double sal) {
		super(fName, lName, ssn);
		this.sal = sal;
	}

//Setters and Getters	
	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return super.toString() + "\nWeely Salary: " + getPaymentAmount();
	}

	@Override
	public double getPaymentAmount() {
		return sal;
	}

}
