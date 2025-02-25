
public class Employee extends Person {
//Data Member
	private String office;
	private double salary;
	private String dateHired;

	public String getOffice() {
		return office;
	}

//Constructors	
	public Employee(String name, String address, long pNum, String eMail, String office, double salary,
			String dateHired) {
		super(name, address, pNum, eMail);
		this.office = office;
		this.salary = salary;
		this.dateHired = dateHired;
	}

	// Getters and Setters
	public void setOffice(String office) {
		this.office = office;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDateHired() {
		return dateHired;
	}

	public void setDateHired(String dateHired) {
		this.dateHired = dateHired;
	}
//Methods

	@Override
	public String toString() {
		return office + ", $" + salary + ", " + dateHired + ", " + super.toString();
	}

}
