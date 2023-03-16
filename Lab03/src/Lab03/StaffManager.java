package Lab03;

public class StaffManager {

	   public static void main(String[] args) {
	     Staff J=new Staff("James Wright",29,"Accounting",365,15);
	     Staff P=new Staff("Peter Coolidge",32,"R&D",1095,7);
	     Staff A=new Staff("Amy Smith",27);
	     System.out.println(J.toString());
	     System.out.println(P.toString());
	     System.out.println(A.toString());
	     System.out.println("---");
	     System.out.println("Same Career?");
	     if(!P.sameCareer(A)){
	        A.setCareer(P);
	        P.sameCareer(A);
	     }
	     J.vacation(10);
	     A.vacation(20);
	     A.vacation(1);
	   }

	}
