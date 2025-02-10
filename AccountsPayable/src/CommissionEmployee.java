
public class CommissionEmployee extends Employee {
	//Data Members
	public double grSales;
	public double comRate;
	
	//Constructor	
	public CommissionEmployee(String fName, String lName, String ssn, double grSales, double comRate) {
		super(fName, lName, ssn);
		this.grSales = grSales;
		this.comRate = comRate;
	}
	//Getters and Setters
	public double getGrSales() {
		return grSales;
	}

	public void setGrSales(int grSales) {
		this.grSales = grSales;
	}

	public double getComRate() {
		return comRate;
	}

	public void setComRate(double comisRate) {
		this.comRate = comisRate;
	}
	@Override
	public String toString() {
		return super.toString() + "\nPayment Amount: " + getPaymentAmount();
	}
	@Override
	public double getPaymentAmount() {
		return (grSales * comRate); //amountEmployeeIsPaid
	}
}
