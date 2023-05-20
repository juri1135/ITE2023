package Lab11.java;
import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.io.*;
import java.util.*;

public class ServiceManagement {
	public static <T extends Hub> int findIndexByNum(ArrayList<T> tList, int num) {
		for(int i=0; i<tList.size(); i++) {
			if(tList.get(i).getnumber()==num){
				return i;
			}
		}
		return -1;
	}
	public static <T extends Hub> T raisePerBox(T t, double rate) {
		//택배 박스 개당 요금 변경, 1.05라면 5% 상승
		t.setPrice(t.getPrice()*rate);
		return t;
	}
	public static <T extends Hub> ArrayList<T> raiseAll(Class<T> c, ArrayList<T> tList, double rate){
		//모든 박스 요금 변경
		for(T elem:tList) {
			elem.setPrice((rate)*elem.getPrice());
		}
		//해당 지역 허브의 기본 택배 요금 변경
		try {
			Field f=c.getField("init_price_per_box");
			double value=f.getDouble(null);
			f.setDouble(null, value*rate);
		}
		catch(NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch(SecurityException e) {
			e.printStackTrace();
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		return tList;
	}
	public static <T extends Hub> void packageBoxes(String descriptions, Class<T> classtype, ArrayList<T> tList) throws InstantiationException, InvocationTargetException {
		try {
			Class<?> cl=classtype;
			Constructor<?> constructor=cl.getConstructor(String.class);
			T hub=(T)constructor.newInstance(descriptions);
			tList.add(hub);
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch(NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch(InstantiationException e) {
			e.printStackTrace();
		}
		//descriptions 배열의 모든 항목을 각 hub별 인스턴스 생성자를 이용해 캡슐화한다.
		//이 때, 인자로 받은 classtype 매개변수로 getconstructor, newinstance 메소드를 호출할 수 있다.
		//tList에는 해당 hub 타입을 가진 인스턴스만 담는다.
		//try/catch를 사용해, 위 메소드를 이용하기 위해 필요한 exception 처리를 수행한다.
		
	}
	public static <T extends Hub, U extends Hub> void changeHub(ArrayList<T> tList, int index, Class<U> classtype, ArrayList<U> uList) throws InvocationTargetException{
			try {
				T t= tList.get(index);
				int num=t.getnumber();
				String des=t.getDescription();
				double price=t.getPrice();
				tList.set(index,null);
				
				Class<?> cla;
				cla=classtype;
				Constructor<?> constructor;
				constructor=cla.getConstructor(String.class);
				U u=(U)constructor.newInstance(des);
				uList.add(u);
				//tList에서 index를 통해 객체를 하나 가져온다.
				
				//getDiscription 메소드를 이용해 description 정보를 가져온다.
				//해당 정보를 이용해 U classtype으로 새로운 객체를 생성한다.
				//tList에서 원래 객체를 null로 세팅한다.
				//새로 만든 객체는 지연 배송에 따른 할인으로 -10%를 적용한다.(0.9)
				//uList에 새로 만든 객체를 추가한다.
			}
			catch(IllegalAccessException e) {
				e.printStackTrace();
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch(NoSuchMethodException e) {
				e.printStackTrace();
			}
			catch(InstantiationException e) {
				e.printStackTrace();
			}
			catch(InvocationTargetException e) {
				e.printStackTrace();
			}
		
	}
}
