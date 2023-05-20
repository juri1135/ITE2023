package Lab11.java;

public class Gangwon extends Hub{
	public static int init_num=20000;
	public static String init_area="Gangwon";
	public static double init_price_per_box=4000;
	public Gangwon(String description) {
		super.setDescription(description);
		super.setArea(init_area);
		super.setNumber(++init_num);
		super.setPrice(init_price_per_box);
	}
	public String toString() {
		return super.toString()+"Area: "+super.getArea()+"\nPrice per box: "+super.getPrice()+"\n";
	}
}
