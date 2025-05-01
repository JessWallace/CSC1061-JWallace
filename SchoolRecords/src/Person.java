
public class Person {
	
//Data Members	
	private String name = "No Name";
	private String address;
	private long pNum;
	private String eMail;
//Constructors
	public Person(String name, String address, long pNum, String eMail) {
		this.name = name;
		this.address = address;
		this.pNum = pNum;
		this.eMail = eMail;
	}
	public Person() {
		this.name = "No Name";
		this.address = "Houseless";
		this.pNum = 0000000;
		this.eMail = "No Electronic Mailbox";
	}
//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getpNum() {
		return pNum;
	}
	public void setpNum(long pNum) {
		this.pNum = pNum;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
//Methods
	@Override
	public String toString() {
		return name + ", " + address + ", " + pNum + ", " + eMail + ", ";
	}

	
	
}
