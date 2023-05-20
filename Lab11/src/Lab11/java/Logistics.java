package Lab11.java;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
public class Logistics {
	public static void main(String args[]) throws InstantiationException, InvocationTargetException {
		String[] new_gyeonggi_boxes= {"Suwon#1","SeongNam#1","YongIn#1","GoSeong#1","GaPyeong#1"};
		String[] new_gangwon_boxes= {"Gangneung#1","Wonju#1"};
		ArrayList<Gyeonggi> gyList=new ArrayList<>();
		ArrayList<Gangwon> gaList=new ArrayList<>();
		ServiceManagement service=new ServiceManagement();
		
		Scanner scan=new Scanner(System.in); 
		
		
		//인스턴스화해서 List에 넣기
		for(String s:new_gyeonggi_boxes) {
			service.packageBoxes(s,Gyeonggi.class, gyList);
		}
		for(String st:new_gangwon_boxes) {
			service.packageBoxes(st,Gangwon.class, gaList);
		}
		System.out.println("*** Oh, delivery price has been increased!! ***");
		//경기랑 강원 배송 요금 다 인상하기
		
		gyList=service.raiseAll(Gyeonggi.class, gyList, 1.05);
		gaList=service.raiseAll(Gangwon.class,gaList,1.05);
		
		
		System.out.println("Which box is important in Gangwon-area?");
		//입력 받은 강원 인스턴스 요금 인상하기
		int inte=scan.nextInt();
		int index=service.findIndexByNum(gaList,inte);
		
		
		if(index==-1) {
			System.out.println("nothing");
		}
		else {
			System.out.println("The box \""+gaList.get(index).getDescription()+"\" is important! be careful!\n");
			Gangwon gan=gaList.get(index);
			gan=service.raisePerBox(gaList.get(index), 1.2);
			gaList.set(index, gan);
		}
		//입력 받은 경기 인스턴스 요금 할인하기
		System.out.println("Which box has the wrong area in Gyeonggi-area?");
		int integ=scan.nextInt();
		int idx=service.findIndexByNum(gyList,integ);
		
		
		if(idx==-1) {
			System.out.println("nothing");
		}
		else {
			System.out.println("The box \""+gyList.get(idx).getDescription()+"\" is actually has to go to Gangwon! late! hurry up!\n");
			
			service.changeHub(gyList, idx, Gangwon.class, gaList);
			
			int ind=gaList.size();
			Gangwon gang=gaList.get(ind-1);
			gang=service.raisePerBox(gaList.get(ind-1), 0.9);
			gaList.set(ind-1, gang);
			
		}
		
		System.out.println("------- Delivery List for Gyeonggi -------");
		
		for(Gyeonggi g:gyList) {
			System.out.println(g+"\n");
		}
		System.out.println("------- Delivery List for Gangwon -------");
		for(Gangwon j:gaList) {
			System.out.println(j+"\n");
		}
		
		scan.close();
		
	}

}
