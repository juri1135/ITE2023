package Lab13;

public class DigitObserver implements Observer{//interface 실행하는 코드니까 update 함수 작성해야 함
	private NumberGenerator num;
	
	public DigitObserver(NumberGenerator num) {
		this.num=num;
		num.addObserver(this);//랜덤한 값 observer에 알려주기
	}
	
	public void update(NumberGenerator generator) {
		System.out.println("DigitObserver: "+generator.getNumber());//random한 값 출력
		try {
			Thread.sleep(100);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
