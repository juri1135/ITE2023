package Lab13;
import java.util.*;
public class RandomNumberGenerator extends NumberGenerator {
	private Random random=new Random();
	private int number;
	
	public int getNumber() {return this.number;}
	
	public void execute() {//numbergenerator abstract method 재정의
		for(int i=0; i<10; i++) {
			this.number=random.nextInt(50);
			notifyObservers();//random 생성한 값 numbergenerator로 넘김
		}
	}
}
