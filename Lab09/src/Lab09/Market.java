package Lab09;
import java.util.*;
public class Market {

	public static void main(String[] args) {
		Scanner keyboard= new Scanner(System.in);
		String name=keyboard.nextLine();
		Wallet wallet=new Wallet(name);
		while(true) {
			try {
				System.out.println();
				wallet.empty();
				System.out.println("Enter price :");
				int price=keyboard.nextInt();
				
				if(price<0) throw new NegativeException();
				
				else if(price>100) throw new TooMuchExpenseException(price);
				
				if(price>wallet.getbalance()) throw new TooMuchExpenseException();
				
				if(price>=0&&price<=wallet.getbalance()) {
					System.out.println("peace~~");
					wallet.increaseIndex(); 
					wallet.decreaseBalance(price);
				}
				
			}
		
			catch(NegativeException e){
				System.out.println(e);
				System.out.println("\tat "+e.getStackTrace());
				System.out.println("oh, sorry!");
			}
			catch(TooMuchExpenseException e) {
				System.out.println(e);
				System.out.println("\tat "+e.getStackTrace());
				System.out.println("oh, my!");
				if(e.getMessage()=="Over the limit!") {
					System.out.println("you pay "+e.getinputNum());
				}
			}
			catch(Exception e) {
				if(e.getMessage()=="Go Home") {
					System.out.println(e);
					System.out.println("\tat "+e.getStackTrace());
					System.out.println("the end...");
					keyboard.close();
					return;
				}
			}
			finally {
				System.out.println(wallet);
				System.out.println("---transaction complete---");
			}
		}
	}
}
