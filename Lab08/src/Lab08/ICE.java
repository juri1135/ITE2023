package Lab08;
import java.time.*;
public class ICE extends Car{
	private static int carNum;
	private static int CO2emission;
	private int GHGPERCAR=35;
	public ICE() {
	}
	public ICE(String name, LocalDate date, int carNum) {
		super(name,date);
		this.carNum+=carNum;
		CO2emission+=carNum*GHGPERCAR;
	}
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(obj.getClass()!=this.getClass()) return false; 
		ICE temp=(ICE)obj;
		if(name.equals(temp.getname())&&date.equals(temp.date)) {
			return true;
		}
		return false;
	}
	public String toString() {
		return "name: "+name+", date: "+date+", carNum: "+carNum;
	}
	public int totalCO2() {
		System.out.println("ICE emit CO2 most when driving");
		return (CO2emission);
	}
}
