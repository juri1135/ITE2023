package Lab04;
public class City {
	private String name;
	private int locationX;
	private int locationY;
	public City(String name, int locationX, int locationY){
		this.name=name;
		this.locationX=locationX;
		this.locationY=locationY;
	}
	public City(String name) {
		this.name=name;
		double randomx=Math.random();
		double randomy=Math.random();
		locationX=(int)(randomx*360);
		locationY=(int)(randomy*360);
	}
	public boolean equals(City city) {
		if(this.name==city.name) {
			if(this.locationX==city.locationX) {
				if(this.locationY==city.locationY) {
					return true;
				}
			}
		}
		return false;
	}
	public String getName() {
		return name;
	}
	public int getLocationX() {
		return locationX;
	}
	public int getLocationY() {
		return locationY;
	}
	public String toString() {
		return name+", "+locationX+", "+locationY;
	}
	public static double distance(City city1, City city2) {
		double dis;
		double a=Math.pow((double)city1.locationX-city2.locationX, 2.0);
		double b=Math.pow((double)city1.locationY-city2.locationY, 2.0);
		double c=a+b;
		dis=Math.sqrt(c);
		return dis;
	}

}
