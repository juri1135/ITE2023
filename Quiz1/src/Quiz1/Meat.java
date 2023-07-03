package Quiz1;

public class Meat {
	private String type;
	private double pricePerKilo;
	private double weightInKilo;
	private double totalValue;
	public Meat(String type, double pricePerKilo, double weightInKilo) {
		this.type=type;
		this.pricePerKilo=pricePerKilo;
		this.weightInKilo=weightInKilo;
		totalValue=pricePerKilo*weightInKilo;
	}
	public String toString() {
		return "\"type: "+type+", Price in Kilo's: $"+pricePerKilo+", Weight: "+weightInKilo+"KG, Total value: $"+totalValue+"\"";
	}
	public double gettotalValue() {
		return totalValue;
	}
}
