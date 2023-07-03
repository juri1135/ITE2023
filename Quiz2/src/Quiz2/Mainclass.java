package Quiz2;

public class Mainclass {
	public static void main(String[] args) {

		Bank bank=new Bank();

		SavingsAccount s=new SavingsAccount(0,3);
		CheckingsAccount c=new CheckingsAccount(0,50);
		
		int a=bank.addAccount(s);
		int b=bank.addAccount(c);
		
		bank.printAccountInformation();

		s.deposit(500);
		c.deposit(100);
		
		c.withdraw(175);
		
		double cbalance=c.getBalance();
		double max=cbalance+50;
		c.withdraw(max);

		bank.printAccountInformation();

	}

}
