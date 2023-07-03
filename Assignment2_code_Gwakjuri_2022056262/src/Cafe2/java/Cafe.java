package Cafe2.java;

import java.util.*;



public class Cafe {
	private double money;
	private Game[] gamesInCafe;
	private Game[] rentedOutGames;
	public Cafe(Game[] games, double startingMoney) {
		gamesInCafe =Arrays.copyOf(games, games.length);//처음엔 입력받은 게임 모두 카페에 있음!
		money=startingMoney;
	}
	public void rentOutGame(String name) {//없을 때 에러, bad일 때 에러, 카페에 있는 게임에서 지우고 빌린 게임에 추가, 돈 추가
		int index=getIndexGameInCafe(name);//몇 번째 자리에 있는가
		if(index==-1) {//cafe에 게임 없으면 에러 메세지 출력
			System.out.println("Sorry, this game does not exist.");
			return;
		}
		else {//카페에 게임이 있다면
			Game game=gamesInCafe[index];//game에 그 game 복사 (privacy leak 방지)
			//quality 확인
			if(gamesInCafe[index].getQuality().equals("Bad")) {
				System.out.println("Sorry, this game's quality is bad.");
				return;
			}
			//돈 받기
			money+=gamesInCafe[index].getPrice();
			money=Math.round(money*1000000000)/1000000000.0;
			//카페에 있는 게임 정보 수정
			Game[] cafegames=new Game[gamesInCafe.length-1];
			for(int i=0; i<index; i++) {//빌릴 게임은 index에 있으니까 그 index에 있는 값 제외하고 새로운 game 배열에 추가
				cafegames[i]=gamesInCafe[i];
			}
			for(int i=index; i<gamesInCafe.length-1; i++) {
				cafegames[i]=gamesInCafe[i+1];//gamesInCafe는 cafegames보다 크기가 하나 큼
			}
			//빌려준 게임 정보 수정
			int idx;
			if(rentedOutGames == null) {//현재 빌려준 게임이 없어서 빈 배열이라면 idx=0
				idx=0;
			}
			else {//빈 배열 아니라면 idx는 게임 갯수
				idx=rentedOutGames.length;
			}
			Game[] rentgames=new Game[idx+1];//하나 더 빌릴 거니까 현재 빌려준 게임 갯수 +1
			if(rentedOutGames == null) {//빈 배열이었다면 첫 번째 원소에 그 게임 추가
				rentgames[0]=game;
			}
			else {
				System.arraycopy(rentedOutGames,0, rentgames, 0, idx);
				//현재 빌려준 게임의 0번째 원소부터 idx개만큼 rentgames의 0번째 원소에 복사하겠다. rentgames의 마지막 index는 idx-1이고(0부터 시작), length는 idx
				rentgames[idx]=game;//그리고 새로운 게임 추가
			}
			//수정한 배열 다시 기존 배열에 할당하기
			gamesInCafe=cafegames;
			rentedOutGames=rentgames;
			System.out.println("Game rented successfully");
			//빌려줬으니 quality 하락
			game.lowerQuality();
		}
		printCafeDetails();
	}
	public void returnGame(String name) {//카페에 있는 게임 추가, 빌린 게임에서 삭제
		int index=getIndexRentedOutGames(name);
		//빌려준 게임인지 확인
		if(index==-1) {
			System.out.println("Sorry, you've never borrowed this game");
			return;
		}
		else{//빌려준 게임이라면
			Game game=rentedOutGames[index];//빌렸던 그 게임 복사해두기
			//카페에 있는 게임 정보 수정
			int gamelength;
			if(gamesInCafe==null) {//카페에 게임이 없다면 gameindex=0;
				gamelength=0;
			}
			else {
				gamelength=gamesInCafe.length;
			}
			Game[] cafegames=new Game[gamelength+1];//반납했으니까 카페에는 게임 하나 추가
			System.arraycopy(gamesInCafe,0, cafegames, 0, gamelength);
			//원래 카페에 있던 게임들 갯수만큼 그대로 cafegames에 복사 cafegames의 length는 gamelength, 마지막 index는 gamelength-1
			cafegames[gamelength]=game;//반납한 게임 추가
			//빌려준 게임 정보 수정
			if(rentedOutGames.length==1) {//빌려준 게임이 1개였다면
				rentedOutGames=null;//빌려준 게임 하나도 없는 걸로 변경
			}
			else {//빌려준 게임이 2개 이상이었다면
				Game[] rentgames=new Game[rentedOutGames.length-1];//반납했으니까 빌려준 게임은 -1
				if(index==0) {//빌려준 게임이 0번 째에 위치해 있다면
					System.arraycopy(rentedOutGames,index+1, rentgames, 0, rentedOutGames.length-1);
					//빌려준 게임에서 0번째 원소(반납한 게임) 한 개만 빼고(rentedOutGames.length-1) 다 복사
				}
				else {
					//System.out.println("length: "+rentedOutGames.length+" index: "+index+" rentgames.length: "+rentgames.length);
					if(index==rentedOutGames.length-1) {//빌려준 게임이 제일 마지막에 위치해 있다면
						System.arraycopy(rentedOutGames,0, rentgames, 0, index);//그냥 0번 째 게임부터 게임 갯수-1까지(length-1 혹은 index) 다 복사
					}
					else {
						System.arraycopy(rentedOutGames,0, rentgames, 0, index);//0번 째부터 반납할 게임 전까지 복사
						System.arraycopy(rentedOutGames,index+1, rentgames, index, rentedOutGames.length-index-1);//반납할 게임의 다음 것부터 마지막 게임까지 복사
					}
				}
				rentedOutGames=rentgames;
			}
			gamesInCafe=cafegames;
		}
		printCafeDetails();
		
	}
	public void buyGame(Game game) {//돈 부족하면 에러 메세지, 돈 충분하면 돈 하락, 카페에 있는 게임에 추가
		//돈 확인
		if(game.getPrice()>money) {
			System.out.println("Not enough funds for the game.");
		}
		else {
			//돈 줄이기
			money-=game.getPrice();
			money=Math.round(money*1000000000)/1000000000.0;
			//카페에 있는 게임 정보 수정
			int boughtgame;
			if(gamesInCafe==null) {
				boughtgame=0;
			}
			else {
				boughtgame=gamesInCafe.length;
			}
			Game[] games=new Game[boughtgame+1];//지금 카페에 있는 게임 갯수보다 하나 크게 array 지정
			System.arraycopy(gamesInCafe,0, games, 0, boughtgame);
			//gamesInCafe의 0번째부터 지금 있는 게임들 다 games에 복사하겠다 games의 마지막 index는 boughtgame-1, length는 bouchtgame
			games[boughtgame]=game;//새 게임 추가
			gamesInCafe=games;
			System.out.println("Game bought successfully");
		}
		printCafeDetails();
	}
	public void printCafeDetails() {
		System.out.println("\nMoney: "+money);
		System.out.println("Games in cafe:");
		if(gamesInCafe==null) System.out.println();
		else {
			for(int i=0; i<gamesInCafe.length; i++) {
				if(gamesInCafe[i]==null) continue;
				System.out.println(gamesInCafe[i]);
			}
		}
		System.out.println("\nGames rented out:");
		if(rentedOutGames==null) System.out.println();
		else {
			for(int i=0; i<rentedOutGames.length; i++) {
				System.out.println(rentedOutGames[i]);
			}
		}
		System.out.println();
	}
	public void repairGame(String name) {//돈 부족하면 에러 메세지, 돈 충분하면 quality good으로 바꾸기
		//수리할 게임이 현재 갖고 있는 게임인지 확인
		int index=getIndexGameInCafe(name);
		if(index==-1) {
			System.out.println("This game does not exists.");
			return;
		}
		else {
			//게임을 갖고 있다면 돈이 있는지 확인
			if(gamesInCafe[index].getRepairCost()>=money){
				System.out.println("Not enough money.");
			}
			else {
				//돈 있다면 수리 비용 지불
				money-=gamesInCafe[index].getRepairCost();
				money=Math.round(money*1000000000)/1000000000.0;
				System.out.println("Repaired successfully, remaining money: "+money);
				gamesInCafe[index].repair();
				printCafeDetails();
			}
		}
	}
	public Game[] getGames() {
		return gamesInCafe;
	}
	private int getIndexGameInCafe(String name) {
		if(gamesInCafe==null) return -1;
		for(int i=0; i<gamesInCafe.length; i++) {
			if(gamesInCafe[i]==null) continue;
			if(name.equals(gamesInCafe[i].getName())) {
				return i;//name private이라서 getname으로 name 받아야 함
			}
		}
		return -1;
	}
	private int getIndexRentedOutGames(String name) {
		if(rentedOutGames==null) return -1;
		for(int i=0; i<rentedOutGames.length; i++) {
			if(rentedOutGames[i]==null) continue;
			if(rentedOutGames[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}

