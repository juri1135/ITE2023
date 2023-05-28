package Lab12;

import java.util.function.Consumer;

import Lab12.ProdCons.Producer;

public class Buffer {
	private double[] data;
	private int loc=0;//버퍼 인덱스
	
	public Buffer(int size) { //size에 맞는 data 배열 생성
		data=new double[size];
		System.out.println("New buffer");
		System.out.println(this+"\n");
	}
	public int getSize() { return data.length; }//현재 data 갯수 출력
	public synchronized String toString() {//현재 data들 소수 셋 째자리에서 반올림해서 출력
		String toReturn="";
		for(double d:data) {
			toReturn+=String.format("%5.2f", d)+" ";
		}
		return toReturn;
	}
	public synchronized void add(Lab12.ProdCons.Producer p, double toAdd) throws InterruptedException{
		//data 배열이 다 차면(loc(배열의 인덱스)가 data 크기보다 크거나 같을 때) buffer is full 출력
		//이게 for문이라면 정해진 횟수만큼 혹은 그것보다 적게 돌아야 한다. 그런데 우리는 buffer가 가득 찬 상태인 동안은 반복문을 계속 돌게 만들고 싶기 때문에 for문은 적절하지 않다. 
		//특히나 다른 thread에서 buffer의 상태가 변한다면 조건이 변하기 때문에 while문을 통해 조건을 계속 확인해주는 게 적절하다.
		while(loc>=data.length) {
			System.out.println("Producer#"+p.getNum()+"try "+loc+" @ Buffer is full.\n");
			wait();//다 찬 상태면 thread wait 상태로 만들기
		}
		System.out.println("Producer#"+p.getNum()+" Adding item on "+loc+": "+toAdd);//다 찬 상태가 아니라면 하나 추가
		data[loc++]=toAdd;//추가하고 인덱스 올려주기
		System.out.println(this);
		System.out.flush();
		notifyAll();//잠든 thread 깨우기
	}
	public synchronized double remove(Lab12.ProdCons.Consumer consumer) throws InterruptedException{
		//data 배열이 비어 있다면(loc(배열의 인덱스)가 0보다 작거나 같을 때) buffer is empty 출력
		//이게 for문이라면 정해진 횟수만큼 혹은 그것보다 적게 돌아야 한다. 그런데 우리는 buffer가 비어 있는 동안은 반복문을 계속 돌게 만들고 싶기 때문에 for문은 적절하지 않다. 
		//특히나 다른 thread에서 buffer의 상태가 변한다면 조건이 변하기 때문에 while문을 통해 조건을 계속 확인해주는 게 적절하다.
		while(loc<=0) {
			System.out.println("Consumer#"+consumer.getNum()+" 0 Buffer is empty.\n");
			wait();//비어 있는 상태면 thread wait 상태로 만들기
		}
		double hold=data[--loc];//하나 비울 거니까 인덱스 하나 줄이고(지울 인덱스로 변경) 그 값 hold에 넣어두기
		System.out.println("Consumer#"+consumer.getNum()+" Removing item on "+loc+": "+hold);//지울 값 출력
		data[loc]=0.0;//지울 인덱스에 값 0.0으로 초기화
		System.out.println(this);
		System.out.flush();
		notifyAll();//잠든 thread 깨우기
		return hold;//지운 값 return
	}
}
