import java.util.Scanner;
public class Lab02 {
	
	public static void main(String[] args) {
		
		Scanner keyboard=new Scanner(System.in);
		String inpu=keyboard.nextLine();
		String[] s1_arr=inpu.split(" ");
		int length=(s1_arr.length);
		Boolean b=inpu.contains("ppt");
		if(b.equals(true)) s1_arr[length-1]="Homework.pdf";
		String ans="";
		String answer="";
		for(int i=0; i<length-1; i++) {
			String str=s1_arr[i];
			String str1=s1_arr[i].substring(0,1);
			String str2=str1.toUpperCase();
			if(i==length-2) {
				ans+=str2+s1_arr[i].substring(1);
				int len=ans.length();
				answer=ans.substring(0,len-1);
			}
			else {
				ans+=str2+".";
			}
		}
		System.out.println("Name Length(Korean) : "+(length-1));
		System.out.println(answer+" submitted "+s1_arr[length-1]);
		
	}

}
