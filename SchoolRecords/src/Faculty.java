
public class Faculty extends Employee {
//Data Members
	private String officeHours;
	private String rank;

//Getters and Setters
	public String getOfficeHours() {
		return officeHours;
	}

	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
//Constructors

	public Faculty(String name, String address, long pNum, String eMail, String office, double salary, String dateHired,
			String officeHours, String rank) {
		super(name, address, pNum, eMail, office, salary, dateHired);
		this.officeHours = officeHours;
		this.rank = rank;
	}
//Methods

	@Override
	public String toString() {
		return "Faculty, " + officeHours + ", " + rank + ", " + super.toString();
	}

}
