package account;
import java.time.*;
public class Account {
	private String name;
	private double yearlyInterest;
	private double balance;
	LocalDate created;
	public Account(String name, double yearlyInterest, LocalDate created) {
		this.name=name;
		this.yearlyInterest=yearlyInterest;
		this.created=created;
		balance=0;
	}
	public double getBalance() {
		return balance;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void increaseYearlyInterest(int byPercent) {
		yearlyInterest+=byPercent;
	}
	public void receiveIncome(double income) {
		balance=balance+income;
	}
	public void receiveInterest() {
		balance=balance+(balance*(yearlyInterest)*0.01)/12;
	}
	public String toString() {
		return "이름: "+name+" 연이자 : "+yearlyInterest+" 잔고: "+balance+" 가입일: "+created;
	}
}
