package Lab13;

public class PrintRandomNumber {
	public static void main(String[] args) {
		NumberGenerator a=new RandomNumberGenerator();//random한 값 
		DigitObserver b=new DigitObserver(a);//값 뭔지 출력하기
		GraphObserver c=new GraphObserver(a);//별 출력
		
		a.execute();//observer에 notify
	}
}
