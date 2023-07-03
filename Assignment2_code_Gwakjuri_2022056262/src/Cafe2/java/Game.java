package Cafe2.java;

public abstract class Game {
	private String name;
	private double price;
	protected int quality; //0~100
	
	Game(String name, double price, int quality){
		this.name=name;
		this.price=price;
		this.quality=quality;
	}
	public String toString() {
		return ("Name: "+name+", Quality: ");
	}
	public void repair() {
		quality=100;
	}
	public double getPrice() {//외부에서 가격 받기 위해 GETTER 함수 추가
		return price;
	}
	public String getName() {//외부에서 이름 받기 위해 GETTER 함수 추가
		return name;
	}
	public int get_Quality() {
		return quality;
	}
	public abstract double getRepairCost();
	public abstract void lowerQuality();
	public abstract String getQuality();
}
