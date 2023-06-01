package Lab13;

public class GraphObserver implements Observer{
	private NumberGenerator num;
	public GraphObserver(NumberGenerator num) {
		this.num=num;
		num.addObserver(this);
		
	}
	
	public void update(NumberGenerator generator) {
		System.out.print("GraphObserver: ");
		for(int i=0; i<generator.getNumber(); i++) {//random한 digit만큼 *출력
			System.out.print("*");
		}
		System.out.println("");
		try {
			Thread.sleep(100);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
