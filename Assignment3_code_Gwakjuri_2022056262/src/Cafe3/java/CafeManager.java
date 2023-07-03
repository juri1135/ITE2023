package Cafe3.java;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

public class CafeManager {
	public static void main(String[] args) {
		Scanner keyboard=new Scanner(System.in);
		
		System.out.println("What is the path of the gamesfile?");
		String file=keyboard.nextLine();
		CSVLoader csvloader= new CSVLoader();
		Game[] games=null;
		
		
		
		try {
			double money=25.0;
			GameCorner gameCorner;
			CoffeeCorner coffeeCorner;
			
			coffeeCorner=new CoffeeCorner(2);
			
			games=csvloader.loadGames(file);
			
			ArrayList<Game> g=new ArrayList<Game>(0);
			
			for(int i=0; i<games.length; i++) {
				g.add(games[i]);
			}
			gameCorner=new GameCorner(g);

			System.out.println("Money: "+money);
			gameCorner.printCafeDetails();
			
			int inpu=0;
			while(true) {
				System.out.println("What would you like to do:");
				System.out.println("1: Rent a game, 2: return a game, 3: repair a game, 4: Buy a new game, 5: Save games, 6: Buy coffee ");
				try{
					inpu=keyboard.nextInt();
				}
				catch(InputMismatchException e) {
					System.out.println("Please enter a valid number.");
					e.printStackTrace();
					System.exit(0);
				}
				if(inpu==1) {
					String name;
					System.out.println("Which game would you like to rent?");
					name=keyboard.next();
					double p=gameCorner.rentOutGame(name);
					if(p!=-1) { money+=p; }
				}
				else if(inpu==2) {
					String name;
					System.out.println("Which game would you like to return?");
					name=keyboard.next();
					gameCorner.returnGame(name);
				}
				else if(inpu==3) {
					String name;
					System.out.println("Which game would you like to repair?");
					name=keyboard.next();
					double p=gameCorner.repairGame(name);
					if(p==-1) {
						System.out.println("This game does not exist");
						continue;
					}
					if(p<money) {
						money-=p;
						money=Math.round(money*1000000000)/1000000000.0;
						int idx=gameCorner.getidx(name);
						gameCorner.rp(idx);
						System.out.println("Repaired successfully, remaining money: "+money);
					}
					else {
						System.out.println("Not enough funds to repair.");
						continue;
					}
				}
				else if(inpu==4) {
					String name;
					double p=0;
					String type;
					System.out.println("What is the name of the game?");
					name=keyboard.next();
					System.out.println("What is the price of the game?");
					try {
						p=keyboard.nextDouble();
						if(money<p) {
							System.out.println("Not enough funds for the game.");
							continue;
						}
					}
					catch(InputMismatchException e) {
						System.out.println("Please enter a valid number.");
						e.printStackTrace();
						System.exit(0);
					}
					System.out.println("What is the type of the game?");
					type=keyboard.next();
					
					if(type.equals("Card")) {
						CardGame card=new CardGame(name,p,100);
						gameCorner.buyGame(card);
					}
					else if(type.equals("Board")) {
						BoardGame board=new BoardGame(name,p,100);
						gameCorner.buyGame(board);
					}
					else if(type.equals("Electronic")) {
						ElectronicGame elec=new ElectronicGame(name,p,100);
						gameCorner.buyGame(elec);
					}
					else {
						System.out.println("Your request is wrong.");
						continue;
					}
					money-=p;
				}

				
				else if(inpu==5) {
					System.out.println("What is the file you want to save to?");
					keyboard.nextLine();
					String file2=keyboard.nextLine();
					ArrayList<Game> savegames=gameCorner.getGames();
					csvloader.saveGames(savegames,file2);
					break;
				}
				else if(inpu==6) {
					System.out.println("What kind of coffee do you want?");
					coffeeCorner.listCoffeeTypes();
					keyboard.nextLine();
					String name=keyboard.nextLine();
					int c=coffeeCorner.makeCoffee(name);
					if(c==-1) {
						System.out.println("We don't sell that coffee");
					}
					else if(c==-2) {
						System.out.println("Our machine is not enough.");
					}
					else {
						System.out.println("The coffee is being preapared on Machine "+c+"!");
						System.out.println("your number is: "+c);
						money+=coffeeCorner.getPrice(name);
					}
					
				}
				else {
					throw new IllegalArgumentException();
				}
				money=Math.round(money*1000000000)/1000000000.0;
				System.out.println("\nMoney: "+money);
				gameCorner.printCafeDetails();
			}
		}
		
		catch(NumberFormatException e) {
			System.out.println("please enter the number.");
			e.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException e) {
			System.out.println("please enter the proper input");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
