
public class Student extends Person {
//Data Members
	private String status;

//Constructors
	public Student(String name, String address, long pNum, String eMail, String status) {
		super(name, address, pNum, eMail);
		this.status = status;
	}

//Getters and Setters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Student, " + super.toString() + status + "\n";
	}

}
