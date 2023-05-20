package Lab11.java;

public class Gyeonggi extends Hub{
	public static int init_num=10000;
	public static String init_area="Gyeonggi";
	public static double init_price_per_box=3000;
	public Gyeonggi(String description) {
		super.setDescription(description);
		super.setArea(init_area);
		super.setNumber(++init_num);
		super.setPrice(init_price_per_box);
	}
	public String toString() {
		return super.toString()+"Area: "+init_area+"\nPrice per box: "+init_price_per_box+"\n";
		
		
	}
}
