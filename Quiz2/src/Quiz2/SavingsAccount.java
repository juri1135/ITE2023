package Quiz2;

public class SavingsAccount implements Account{
	private double balance;

	private double Interest;
		
	public void applyInterest() { // Applies the interest over the current balance, this is done every time money is deposited to this account type.
		Interest=balance+balance*Interest;
	}
	public String toString() { // Prints the current balance and interestrate, as well as the account type.
		return "<SavingAccount> balance: "+balance+" Interest: "+Interest+"\n";
	}
		
	public SavingsAccount(double balance, double interestRate) {
		this.balance=balance;
		this.Interest=interestRate;
	}
	public double getBalance() { return balance;}
	
	public void deposit(double amount) {
		balance+=amount;
	}
	public void withdraw(double amount) {
		balance-=amount;
	}
	
}
