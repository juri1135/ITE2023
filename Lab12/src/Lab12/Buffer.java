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
		//if문을 사용한다면 한 thread가 깨어나서 buffer의 상태를 확인할 때 이미 다른 thread를 통해 buffer가 바꼈을 수도 있음.
		//이 경우 thread race condition을 만들어낸다. 또한 thread가 대기 상태일 때 예기치 않게 thread가 깨어날 수도 있는데 while문이라면 다시 조건 확인 후
		//thread를 재울 수 있음 그러나 if문이라면 이미 조건 확인이 끝났기 때문에 다시 조건을 확인하는 과정 없이 진행한다.
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
		//if문을 사용한다면 한 thread가 깨어나서 buffer의 상태를 확인할 때 이미 다른 thread를 통해 buffer가 바꼈을 수도 있음.
		//이 경우 thread race condition을 만들어낸다. 또한 thread가 대기 상태일 때 예기치 않게 thread가 깨어날 수도 있는데 while문이라면 다시 조건 확인 후
		//thread를 재울 수 있음 그러나 if문이라면 이미 조건 확인이 끝났기 때문에 다시 조건을 확인하는 과정 없이 진행한다.
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
