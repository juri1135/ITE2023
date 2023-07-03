package Quiz2;

public class Bank {
	public Account[] accounts=new Account[2];
	public int addAccount(Account account) { // returns the index of the account in the accounts array
		int idx;
		if(accounts==null) {
			idx=1;
			accounts[0]=account;
		}
		else {
			accounts[1]=account;
			idx=2;
		}
		return idx;
	}
	public void deposit(int index, double amount) {
		accounts[index].deposit(amount);
	}

	public void withdraw(int index, double amount) {
		accounts[index].withdraw(amount);
	}

	public void printAccountInformation() { //uses the toString() method of each account to print the account's information
		if(accounts==null) {
			System.out.println("null");
		}
		for(int i=0; i<accounts.length; i++) {
			System.out.println(accounts[i]);
		}
	}
}
