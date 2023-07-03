package Cafe3.java;


public class CardGame extends Game{
	CardGame(String name, double price, int quality){
		super(name, price, quality);
	}
	public double getRepairCost() {
		return (100-quality)*0.02;
	}
	public void lowerQuality() {
		quality-=30;
	}
	public String getQuality() {
		if(quality>60) return "Good";
		else if(quality>=25) return "Okay";
		else return "Bad";
	}
	public String toString() {
		return super.toString()+getQuality()+", Price: "+getPrice()+", type: Card";
	}
}