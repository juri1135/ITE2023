package Lab09;

public class TooMuchExpenseException extends Exception{
	private int inputNum;
	TooMuchExpenseException(){
		super("Not enough balance");
	}
	TooMuchExpenseException(int inputNum){
		super("Over the limit!");
		this.inputNum=inputNum;
	}
	public int getinputNum() {
		return inputNum;
	}
}
