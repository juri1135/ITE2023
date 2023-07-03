package Test;
import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		Scanner keyboard=new Scanner(System.in);
		String str=keyboard.nextLine();
		String str2[]=str.split(" ");
		for(int i=0; i<3; i++) {
			String replac=str2[i].substring(0,1);
			String REPLA=replac.toUpperCase();
			str2[i]=str2[i].replace(replac, REPLA);
		}
		for(int i=0; i<4; i++) {
			System.out.print(str2[i]+" ");
		}
	}

}
