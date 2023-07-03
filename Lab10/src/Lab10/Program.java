package Lab10;
import java.util.*;
public class Program {
	
	public static void main(String[] args) {
		
		Dog dog=new Dog();
		Tiger tiger=new Tiger();
		Turtle turtle=new Turtle();
		Animal[] animal= new Animal[3];
		animal[0]=dog;
		animal[1]=tiger;
		animal[2]=turtle;
		
		Person person=new Person(){
			private int hp=100;
			public void control(Animal animal) {
				System.out.println("You have overpowered the "+animal.getName());
				if(animal.getClass()==Dog.class) hp-=10;
				else if(animal.getClass()==Tiger.class) hp-=80;
			}
			public void showInfo() {
				System.out.println("Person HP: "+hp);
			}
		};
		
		showResult(animal, person);
	}
	
	public static void showResult(Animal[] animals, Person p){
		for(int i=0; i<3; i++) {
			System.out.println("Animal"+(i+1)+":"+animals[i].getName());
			if(animals[i]instanceof Barkable) {
				System.out.println("Animal"+(i+1)+" barked "+((Barkable)(animals[i])).bark());
			}
			p.control(animals[i]);
			p.showInfo();
		}
	}
}
