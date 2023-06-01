package Lab13;
import java.util.*;

public abstract class NumberGenerator {
	private ArrayList<Observer> observers=new ArrayList<Observer>();//별 담을 list
	
	public abstract int getNumber();
	public abstract void execute();//abstract method니까 이 class를 상속받은 class에서 함수 body작성해야 함
	
	public void addObserver(Observer observer) {
		observers.add(observer);//list에 추가
	}
	public void deleteObserver(Observer observer) {
		observers.remove(observer);//list에서 값 지우기
	}
	public void notifyObservers() {
		for(int i=0; i<observers.size(); i++) {
			observers.get(i).update(this);//최신 값 observer에 전달
		}
	}
	
}
