
public class CheckingAccount extends Account {
//Data Members
	private double overdraftLimit;
	
	public CheckingAccount(int id, double balance, double overdraftLimit) {
		super(id, balance);
		this.overdraftLimit = overdraftLimit;
	}

//Getters and Setters
	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	public double withdraw(double amount) {
		if (amount > this.getBalance() + this.overdraftLimit) {
			System.out.println("Unable to withdraw, amount is over the balance.");
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

	@Override
	public String toString() {
		return "Checking Account Overdraft Limit: " + overdraftLimit + ", " + super.toString();
	}

}
