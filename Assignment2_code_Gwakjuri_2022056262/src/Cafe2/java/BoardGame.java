package Cafe2.java;

public class BoardGame extends Game{
	BoardGame(String name, double price, int quality){
		super(name, price, quality);
	}
	public double getRepairCost() {
		return (100-quality)*0.04;
	}
	public void lowerQuality() {
		quality-=25;
	}
	public String getQuality() {
		if(quality>70) return "Good";
		else if(quality>=50) return "Okay";
		else return "Bad";
	}
	public String toString() {
		return super.toString()+getQuality()+", Price: "+getPrice()+", type: Board";
	}
}
