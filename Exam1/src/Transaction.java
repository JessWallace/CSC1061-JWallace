import java.time.LocalDate;

public class Transaction {
//Data Member
	private LocalDate date = LocalDate.now();
	private Character type;
	private double amount;
	private double newBalance;

	// Constructor
	public Transaction(LocalDate date, Character type, double amount, double balance) {
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.newBalance = balance;
	}

	@Override
	public String toString() {
		return "Transaction Date: " + date + ", Type: " + type + ", Amount: " + amount + ", Balance: "
				+ ", New Balance: " + newBalance + ".";
	}

}
