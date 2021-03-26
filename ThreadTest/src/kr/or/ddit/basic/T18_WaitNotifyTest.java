package kr.or.ddit.basic;

public class T18_WaitNotifyTest {
/*
 * wait()메서드 => 동기화 영역에서 락을 풀고 wait-set영역(공유객체별 존재)으로
 * 				 이동시킨다.
 * notify() 또는 notifyAll() 메서드 => wait-set 영역에 있는 스레드를 꺠워서
 * 								     실행될 수 있도록 한다.
 * 						(notify()는 하나, notifyAll()은 모두 깨운다.)
 * 
 * => wait()와 notify(), notifyAll()메서드는 동기화 영역에서만 실행할 수 있고,
 * 	  Objcet클래스에서 제공하는 메서드이다.
 */
	public static void main(String[] args) {
		workObject workObj = new workObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체
class workObject{
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업 중..");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업 중..");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

// workObj의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread {
	private workObject workObj;
	
	public ThreadA(workObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

// workObj의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread {
	private workObject workObj;
	
	public ThreadB(workObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}





























