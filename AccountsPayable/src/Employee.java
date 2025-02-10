
public class Employee {
	//FirstName
	private String fName;
	//LastName
	private String lName;
	//SocialSecurityNumber
	private String ssn;
	
//Constructor
	public Employee(String fName, String lName, String ssn) {
		this.fName = fName;
		this.lName = lName;
		this.ssn = ssn;
	}
	public Employee() {
		fName = "No First Name Given.";
		lName = "No Last Name Given.";
		ssn = "No Social Security Number Provided.";
	}
// Getters and Setters
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
//Method
	public double getPaymentAmount() {
		return 0; //amountEmployeeIsPaid
	}
	@Override
	public String toString() {
		return "Employee Full Name: " + fName + " " + lName + "\nSocial: " + ssn;
	}
	
	
}
