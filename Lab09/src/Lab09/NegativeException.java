package Lab09;

public class NegativeException extends Exception{
	NegativeException(){
		super("price must be positive");
	}
	NegativeException(String message){
		super(message);
	}
}
