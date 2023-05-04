package Lab09;

public class Wallet {
	private String name;
	private int balance;
	private int txIndex;
	Wallet(String name){
		this.name=name;
		txIndex=0;
		balance=100;
	}
	public int getbalance() {
		return balance;
	}
	public void increaseIndex() {
		txIndex++;
	}
	public void decreaseBalance(int expense) {
		balance-=expense;
	}
	public String toString() {
		return "name: "+this.name+",#"+this.txIndex+",balance: "+this.balance;
	}
	public void empty() throws Exception{
		if(balance<=0) throw new Exception("Go Home");
	}
}
