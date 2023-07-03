package Cafe3.java;

import java.util.*;


public class GameCorner {
	private ArrayList<Game> gamesInCafe;
	private ArrayList<Game> rentedOutGames;
	public int getidx(String name) {
		return getIndexGameInCafe(name);
	}
	public void rp(int i) {
		gamesInCafe.get(i).repair();
	}
	public GameCorner(ArrayList<Game> games) {
		gamesInCafe = new ArrayList<Game>(0);
		rentedOutGames = new ArrayList<Game>(0);
		for(int i=0; i<games.size(); i++) {
			gamesInCafe.add(games.get(i));//인자로 넘어온 게임들 하나씩 gamesInCafe에 add하기
		}
	}
	public double rentOutGame(String name) {//없을 때 에러, bad일 때 에러, 카페에 있는 게임에서 지우고 빌린 게임에 추가, 돈 추가
		int index=getIndexGameInCafe(name);//몇 번째 자리에 있는가
		if(index==-1) {//cafe에 게임 없으면 에러 메세지 출력
			System.out.println("Sorry, this game does not exist.");
			return -1;
		}
		else {//카페에 게임이 있다면
			Game game=gamesInCafe.get(index);
			//quality 확인
			if(gamesInCafe.get(index).getQuality().equals("Bad")) {
				System.out.println("Sorry, this game's quality is bad.");
				return -1;
			}
			//카페에 있는 게임 정보 수정
			gamesInCafe.remove(index);
			//빌려준 게임 정보 수정
			rentedOutGames.add(game);
			System.out.println("Game rented successfully");
			//돈 받기
			return game.getPrice()*0.5;
		}
		
	}
	public void returnGame(String name) {//카페에 있는 게임 추가, 빌린 게임에서 삭제
		int index=getIndexRentedOutGames(name);
		//빌려준 게임인지 확인
		if(index==-1) {
			System.out.println("Sorry, you've never borrowed this game");
			return;
		}
		else{//빌려준 게임이라면
			System.out.println("Game returned successfully");
			Game game=rentedOutGames.get(index);//빌렸던 그 게임 복사해두기
			//빌려줬으니 quality 하락
			game.lowerQuality();
			//카페에 있는 게임 정보 수정
			gamesInCafe.add(game);
			//빌려준 게임 정보 수정
			rentedOutGames.remove(index);
		}
	}
	public void buyGame(Game game) {
		//새로 구매한 게임을 cafe에 넣고 성공 문구 출력
		gamesInCafe.add(game);
		System.out.println("Game bought successfully");
	}
	public void printCafeDetails() {
		System.out.println("Games in cafe:");
		if(gamesInCafe==null) System.out.println();
		else {
			for(int i=0; i<gamesInCafe.size(); i++) {
				System.out.println(gamesInCafe.get(i));
			}
		}
		System.out.println("\nGames rented out:");
		if(rentedOutGames==null) System.out.println();
		else {
			for(int i=0; i<rentedOutGames.size(); i++) {
				System.out.println(rentedOutGames.get(i));
			}
		}
	}
	public double repairGame(String name) {//돈 부족하면 에러 메세지, 돈 충분하면 quality good으로 바꾸기
		//수리할 게임이 현재 갖고 있는 게임인지 확인
		int index=getIndexGameInCafe(name);
		if(index==-1) return -1;
		else {
			return gamesInCafe.get(index).getRepairCost();
		}
	}
	public ArrayList<Game> getGames() {
		return gamesInCafe;
	}
	private int getIndexGameInCafe(String name) {
		if(gamesInCafe.isEmpty()) return -1;
		for(int i=0; i<gamesInCafe.size(); i++) {
			if(name.equals(gamesInCafe.get(i).getName())) {
				return i;//name private이라서 getname으로 name 받아야 함
			}
		}
		return -1;
	}
	private int getIndexRentedOutGames(String name) {
		if(rentedOutGames.isEmpty()) return -1;
		for(int i=0; i<rentedOutGames.size(); i++) {
			if(rentedOutGames.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
}
