package Cafe3.java;
import java.util.*;

public class CoffeeCorner implements Observer{
	private int coffeeIndex;
	private HashMap<String, Double> coffeeTypes=new HashMap<>();
	private ArrayList<CoffeeMachine> machines;
	
	public CoffeeCorner(int machineAmount) {
		coffeeIndex=0;
		machines = new ArrayList<CoffeeMachine>(machineAmount);
		for(int i=0; i<machineAmount; i++) {
			machines.add(new CoffeeMachine(i+1, Integer.toString(i+1)));
		}
		coffeeTypes.put("Capucino",3.75);
		coffeeTypes.put("Americano",2.5);
		coffeeTypes.put("Latte",3.25);
	}
	
	private class CoffeeMachine extends Thread implements Observable{
		private ArrayList<Observer> observers;	
		private int index;		
		private String machineName;
		public CoffeeMachine(int index, String name) {//make coffee를 하면 만든다
			observers=new ArrayList<Observer>(0);
			this.index=index;
			machineName=name;
		}
		public void run() {
			subscribe(CoffeeCorner.this);
			try {
				sleep(3000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=20; i<100; i+=20) {
				System.out.println("Coffee with number: "+index+" is "+i+"% done.");
				try {
					sleep(2000);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Coffee with number: "+index+" is ready for pickup at Machine "+machineName);
			update(index, machineName);
			unsubscribe(CoffeeCorner.this);
		}
		@Override
		public void subscribe(Observer observer) {
			observers.add(observer);
			
		}
		@Override
		public void unsubscribe(Observer observer) {
			observers.remove(observer);
		}
		public int getIndex() {
			return index;
		}

	}
	public int makeCoffee(String type) {
		if(type.equals("Capucino")||type.equals("Americano")||type.equals("Latte")) {
			if(machines.size()==0) return -2;
			else {
				int index=machines.get(0).getIndex();
				machines.get(0).start();
				machines.remove(0);
				return index;
			}
		}
		else {
			return -1;
		}
	}
	public double getPrice(String coffeeName) { return coffeeTypes.get(coffeeName);}
	public void listCoffeeTypes() {
		coffeeTypes.forEach((k,v)->System.out.println(k+", Price: "+v));
	}
	public void update(int coffeeIndex, String machineName) {
		machines.add(new CoffeeMachine(coffeeIndex,machineName));
	}
}
