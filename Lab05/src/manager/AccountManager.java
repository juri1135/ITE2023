package manager;
import java.time.LocalDate;
import account.*;
public class AccountManager {

	public static void main(String[] args) {
		LocalDate created=LocalDate.of(2021, 12, 01);
		Account juri=new Account("Gwak",5,created);
		System.out.println(juri);
		int year=2021,month=12,date=01;
		boolean check=false;
		
		while(juri.getBalance()<10000) {
			
			int juriyear=created.getYear();
			int jurimonth=created.getMonthValue();
			
			//매달 지날 때마다 이자와 소득
			juri.receiveIncome(100.0);
			juri.receiveInterest();
			
			//이벤트 진행 1년 이상 가입했는지, 그리고 매 1월에 진행
			if(year-juriyear>=1&&month<=jurimonth&&month==1) {
				double lotto=Math.random();
				int lottonum=(int)(lotto*10);
				
				double win=Math.random();
				int winner=(int)(win*10);
				
				if(lottonum==winner) {
					System.out.println("이벤트 당첨!");
					juri.receiveIncome(100.0);
				}
			}
			
			//가입한지 3년이 지났으면 이자율 증가
			if(!check&&year-juriyear>3&&month==jurimonth) {
				juri.increaseYearlyInterest(2);
				System.out.println("가입 후 3년이 지나서 이자율이 2% 인상되었습니다.");
				check=true;
			}			
			month++;
			if(month==13) {
				year++;
				month=1;
			}
		}
		System.out.println(juri+", 1억 모으기 끝: "+year+"-"+month+"-"+date);
	}

}
