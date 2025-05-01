
public class Staff extends Employee {
//Data Members
	private String title;

//Constructors
	public Staff(String name, String address, long pNum, String eMail, String office, double salary, String dateHired,
			String title) {
		super(name, address, pNum, eMail, office, salary, dateHired);
		this.title = title;
	}

//Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//Methods
	@Override
	public String toString() {
		return "Staff, " + super.toString() + title + "\n";
	}

}
