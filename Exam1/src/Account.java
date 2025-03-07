import java.time.LocalDate;

abstract class Account {
	// Data Members
	private int id = 0;
	private double balance = 0;
	private double annualInterestRate = 0;
	private double monthlyInterestRate;
	private LocalDate dateCreated = LocalDate.now();
	private String name;
	private Transaction transaction;
	private String transactions;
	

	// Constructors
	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public Account(int id, double balance, String name) {
		this.id = id;
		this.balance = balance;
		this.name = name;
	}

	public Account() {
	}
	// Getters and Setters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;		
		transactions = "\n" + transaction.toString();

	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	// Methods
	public double getMonthlyInterestRate() {
		monthlyInterestRate = this.annualInterestRate / 12;
		return monthlyInterestRate;
	}

	public double getMonthlyInterest() {
		double monthlyInterest = balance * (monthlyInterestRate / 100);
		return monthlyInterest;
	}

	public double withdraw(double amount) {
		balance = balance - amount;
		Transaction currentTransaction = new Transaction(dateCreated, 'W', amount, balance);
		currentTransaction.toString();
		this.setTransaction(currentTransaction);
		return balance;
	}

	public double deposit(double amount) {
		balance += amount;
		Transaction currentTransaction = new Transaction(dateCreated, 'D', amount, balance);
		currentTransaction.toString();
		this.setTransaction(currentTransaction);
		return balance;
	}

	@Override
	public String toString() {
		return "Account ID: " + id + ", Balance: " + balance + ", Monthly Interest Rate: " + monthlyInterestRate
				+ ", Date created: " + dateCreated + ", Name: " + name +".";
	}

}
