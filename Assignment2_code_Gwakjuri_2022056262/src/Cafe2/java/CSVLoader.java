package Cafe2.java;

import java.io.*;
import java.util.*;
import java.lang.*;

public class CSVLoader {
   CSVLoader(){}
   public Game[] loadGames(String fileName){
      BufferedReader bufferedReader=null;
      Game[] games=new Game[10];
      try{
         
         bufferedReader=new BufferedReader(new FileReader(fileName));
         String line="";
         int i=0;
         while((line=bufferedReader.readLine())!=null) {
            String[] str=line.split(",");
            String type=str[0];//type 먼저 받고 type에 따라서 game 배열에 다르게 저장
            String name=str[1];
            Double price=0.0;
            Integer quality=0;
            try {
         	   price=Double.parseDouble(str[2]);
         	   quality=Integer.parseInt(str[3]);
            }
            catch(NumberFormatException e){
         	   System.out.println("please enter the number.");
                e.printStackTrace();
                System.exit(0);
            }
            if(type.equals("Board")){
               
               BoardGame board=new BoardGame(name,price,quality);
               games[i++]=board;
            }
            else if(type.equals("Electronic")){
               
               ElectronicGame elec=new ElectronicGame(name,price,quality);
               games[i++]=elec;
            }
            else if(type.equals("Card")) {
               
               CardGame card=new CardGame(name,price,quality);
               games[i++]=card;
            }
            else {
               throw new IOException();
            }
         }
         
      }
      catch(FileNotFoundException e) {
          System.out.println("File was not found.");
          e.printStackTrace();
          System.exit(0);
       }
      
      catch(NumberFormatException e) {
         System.out.println("please enter the number.");
         e.printStackTrace();
         System.exit(0);
      }
      catch(IOException e){
         System.out.println("Error reading from file.");
         e.printStackTrace();
         System.exit(0);
      }
      finally {
         if(bufferedReader!=null) {
            try {
               bufferedReader.close();
            }
            catch(IOException e) {
               System.out.println("Error closing from file.");
               e.printStackTrace();
               System.exit(0);
            }
         }
      }
      return games;
   }
   
   public void saveGames(Game[] games, String fileName){
	   PrintWriter file=null;
	   try {
		   //"C:\Users\USER\OneDrive\바탕 화면\games-1.csv"
		   //file=new PrintWriter(new FileWriter(fileName));
		   file=new PrintWriter(new FileOutputStream(fileName));
		   for(int i=0; i<games.length; i++) {
			   if(games[i]==null) continue;
			   if(games[i] instanceof BoardGame) {
				   BoardGame boardGame=(BoardGame) games[i];
			       file.print("Board,"+boardGame.getName()+","+boardGame.getPrice()+","+boardGame.get_Quality()+"\n");
			   }
			   else if (games[i] instanceof ElectronicGame) {
		           ElectronicGame electronicGame = (ElectronicGame) games[i];
		           file.print("Electronic," + electronicGame.getName() + "," + electronicGame.getPrice() + "," + electronicGame.get_Quality()+"\n");
		       } 
			   else if (games[i] instanceof CardGame) {
		           CardGame cardGame = (CardGame) games[i];
		           file.print("Card," + cardGame.getName() + "," + cardGame.getPrice() + "," + cardGame.get_Quality()+"\n");
			   }
		   }
		   System.out.println("Games saved succesfully");
	   }
	   catch(IOException e){
		   System.out.println("Error saving to file.");
		   e.printStackTrace();
           System.exit(0);
	   }
	   finally {
		   if(file!=null) {
			   file.close();
		   }
	   }
   }
}
