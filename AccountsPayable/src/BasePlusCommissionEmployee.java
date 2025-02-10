
public class BasePlusCommissionEmployee extends CommissionEmployee{
	//Data Members
	public double basePay;
	//Constructor
	public BasePlusCommissionEmployee(String fName, String lName, String ssn, double grSales, double comRate, double basePay) {
		super(fName, lName, ssn, grSales, comRate);
		this.basePay = basePay;
	}
	//Setters and Getters
	public double getBasePay() {
		return basePay;
	}

	public void setBasePay(double basePay) {
		this.basePay = basePay;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nPayment Amount: " + getPaymentAmount();
	}
	@Override
	public double getPaymentAmount() {
		return (grSales*comRate + basePay); //amountEmployeeIsPaid
	}
	
}
