/*
	wait() => 동기화 영역에서 락을 품고 Wait-Set영역 (공유객체별 존재)으로 이동
	
	notify() / notifyAll() => Wait-Set 영역에 있는 Thread를 깨워서 실행
	(Notify() = 하나
	NotifyAll() = Wait_set에 존재하는 모든 Thread
	
	==> Wait과 notufy(), notifyAll()메서드는 동기화 영역에서만 실행
	 	Object클래스에서 제공되는 메서드
 */
package kr.or.ddit.basic;

public class T17WaitNotifyTest {
	public static void main(String[] args) {
		WorkObject workobj = new WorkObject();

		ThreadA thA = new ThreadA(workobj);
		ThreadB thB = new ThreadB(workobj);

		thA.start();
		thB.start();
	}
}

//공유객체용 클래스
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메소드 처리 중...");

		notify();

		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void methodB() {
		System.out.println("methodB()메소드 처리 중...");

		notify();

		try {
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

//WorkObject의 methodA() 메서드만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료...");
	}
}

class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료...");
	}
}