package Quiz2;

public class CheckingsAccount implements Account {
	private double balance;

	private double overdraftLimit; // the limit of how much below 0 your balance can go.

	
	public String toString() { // prints the balance, overdraftlimit and the account type.
		return "<CheckingsAccount> Balance: "+balance+" overdraftlimit: "+overdraftLimit+"\n";
	}
	public CheckingsAccount(double balance, double overdraftLimit) {
		this.balance=balance;
		this.overdraftLimit=overdraftLimit;
	}
	public void deposit(double amount) {
		balance+=amount;
	}
	public void withdraw(double amount) {
		if(balance-amount<-overdraftLimit) {
			System.out.println("You can't withdraw.");
		}
		else{
			balance-=amount;
		}
	}
	public double getBalance() { return balance;}
}
