package Lab11.java;

public class Hub {
	private int number;//박스 시리얼 번호
	private String description;//박스 객체의 설명
	private String area;//배송 허브 위치
	private double price_per_box;//박스 개당 배송 단가
	public int getnumber() {
		return number;
	}
	public String getDescription() {
		return description;
	}
	public String getArea() {
		return area;
	}
	public double getPrice() {
		return price_per_box;
	}
	public void setNumber(int number) {
		this.number=number;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setArea(String area) {
		this.area=area;
	}
	public void setPrice(double price) {
		this.price_per_box=price;
	}
	public String toString() {
		return "Box Number : "+number+"\nDescription : "+description+"\n";
	}
}
