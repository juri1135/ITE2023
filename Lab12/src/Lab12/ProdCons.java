package Lab12;

public class ProdCons {
	private Buffer buffer;
	private Producer[] producer;
	private Consumer[] consumer;
	public ProdCons() {
		buffer= new Buffer(5); //버퍼 객체 생성 및 data 크기를 5로 설정
		producer=new Producer[2];//producer thread와 consumer thread를 각각 두 개씩 생성
		consumer=new Consumer[2];
		for(int i=0; i<2; i++) {
			producer[i]=new Producer(buffer);//같은 버퍼를 공유하는 producer, consumer 배열 변수에 저장
			consumer[i]=new Consumer(buffer);
		}
		
	}
	public class Producer extends Thread{
		private static int serial=0;//호출될 때마다 인덱스 증가시켜야 해서 static으로 선언
		private int pNum;//인덱스
		private final Buffer buffer;
		public Producer(Buffer buffer) {
			this.buffer=buffer;//매개변수로 전역변수 buffer 초기화
			pNum=serial++;//이번 인덱스는 호출된 횟수만큼
		}
		public void produce() throws InterruptedException{
			//buffer의 add를 호출하여 무작위로 생성된 값(0~100 실수)을 Buffer 배열에 추가(배열의 크기만큼 반복)
			for(int i=0; i<buffer.getSize(); i++) {
				System.out.println("Producer#"+this.pNum+":"+this);
				buffer.add(this, Math.random()*100);//buffer에 1~100에 해당하는 난수 추가
			}
		}
		public void run() {
			//produce 호출
			try {
				produce();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		public int getNum() { return pNum;}//해당 인덱스 return
	}
	public class Consumer extends Thread{
		private final Buffer buffer;
		private static int serial=0;//호출될 때마다 인덱스 증가시켜야 해서 static으로 선언
		private int pNum;//인덱스
		public Consumer(Buffer buffer) {
			this.buffer=buffer; //매개변수로 전역변수 buffer 초기화
			pNum=serial++;
		}
		public void consume() throws InterruptedException{
			//buffer의 remove를 호출해서 Buffer 배열에서 삭제(배열의 크기만큼 반복)
			for(int i=buffer.getSize(); i>0; i--) {
				System.out.println("Consumer#"+this.pNum+":"+this);
				buffer.remove(this);
			}
		}
		public void run() {//실행할 부분 overriding
			try {
				consume();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		} 
		public int getNum() { return pNum;}
	}
	public void startThread() {
		for(int i=0; i<buffer.getSize(); i++) {//배열 변수에 저장된 모든 thread start해야 하니까 buffer size 받아서 그 크기만큼 반복
			producer[i].start();//thread의 start는 run을 호출
			consumer[i].start();
		}
	}
}
