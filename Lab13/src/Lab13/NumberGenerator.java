package Lab13;
import java.util.*;

public abstract class NumberGenerator {
	private ArrayList<Observer> observers=new ArrayList<Observer>();
	
	public abstract int getNumber();
	public abstract void execute();
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}
	public void notifyObservers() {
		for(int i=0; i<observers.size(); i++) {
			observers.get(i).update(this);//최신 값 observer에 전달
		}
	}
	
}
