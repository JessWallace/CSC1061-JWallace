
public class SavingsAccount extends Account {
//Data Members
	private double minimumBalance;

//Getters and Setters
	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

//Constructors
	public SavingsAccount(int id, double balance, double minimumBalance) {
		super(id, balance);
		this.minimumBalance = minimumBalance;
	}

//Methods
	@Override
	public String toString() {
		return "Savings Account Minimum Balance: " + minimumBalance + ", " + super.toString();
	}

	@Override
	public double withdraw(double amount) {
		if ((this.getBalance() - amount) < this.minimumBalance) {
			System.out.println("Must maintain minimum balance of: " + this.minimumBalance
					+ ". Please pick an amount between: " + (this.getBalance() - this.minimumBalance) + " - 1.");
			return this.getBalance();
		} else {
			this.setBalance(this.getBalance() - amount);
			Transaction currentTransaction = new Transaction(getDateCreated(), 'W', amount, getBalance());
			currentTransaction.toString();
			this.setTransaction(currentTransaction);
			return this.getBalance();
		}
	}

	@Override
	public double deposit(double amount) {
		// TODO Auto-generated method stub
		return super.deposit(amount);
	}

}
