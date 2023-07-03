package Quiz1;
import java.util.Scanner;
public class Butchery {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double total=0;
		for(int i=0; i<3; i++) {
			System.out.println("What is the type of the meat?");
			String name=scanner.next();
			System.out.println("What is the prie per Kilo?");
			double price=scanner.nextDouble();
			System.out.println("What is the total weight?");
			double weight=scanner.nextDouble();
			Meat m=new Meat(name,price,weight);
			System.out.println(m);
			System.out.println();
			total+=m.gettotalValue();
		}
		System.out.println("totalValue: "+total);
	}

}
