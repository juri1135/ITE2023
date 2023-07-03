package Cafe2.java;

import java.io.*;
import java.util.*;

public class CafeManager {

	public static void main(String[] args) {
		Scanner keyboard=new Scanner(System.in);
		
		System.out.println("What is the path of the gamesfile?");
		String file=keyboard.nextLine();
		CSVLoader csvloader= new CSVLoader();
		Game games[]=null;
		
		try {
			games=csvloader.loadGames(file);
			Cafe cafe=new Cafe(games,25.0);
			cafe.printCafeDetails();
			
			int inpu=0;
			while(inpu!=5) {
				System.out.println("What would you like to do:");
				System.out.println("1: Rent a game, 2: return a game, 3: repair a game, 4: Buy a new game, 5: Save games ");
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
					cafe.rentOutGame(name);
				}
				else if(inpu==2) {
					String name;
					System.out.println("Which game would you like to return?");
					name=keyboard.next();
					cafe.returnGame(name);
				}
				else if(inpu==3) {
					String name;
					System.out.println("Which game would you like to repair?");
					name=keyboard.next();
					cafe.repairGame(name);
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
						cafe.buyGame(card);
					}
					else if(type.equals("Board")) {
						BoardGame board=new BoardGame(name,p,100);
						cafe.buyGame(board);
					}
					else if(type.equals("Electronic")) {
						ElectronicGame elec=new ElectronicGame(name,p,100);
						cafe.buyGame(elec);
					}
					else {
						System.out.println("Your request is wrong.");
					}
					
				}
				else if(inpu==5) {
					System.out.println("What is the file you want to save to?");
					keyboard.nextLine();
					String file2=keyboard.nextLine();
					Game[] savegames=null;
					savegames=cafe.getGames();
					csvloader.saveGames(savegames,file);
				}
				else {
					throw new IllegalArgumentException();
				}
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
