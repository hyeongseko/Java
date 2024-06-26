package kr.or.ddit.basic;

/*
 * 스레드 생성 방법
 * @author 30622-PC
 */
public class T02ThreadTest {
	public static void main(String[] args) {
		// 1. Thread클래스를 상속한 class의 인스턴스를 생성 후
		// 이 인스턴스의 start()메서드 실
		Thread t1 = new MyThread1();
		t1.start(); // 스레드 구동

		// 2. Runnable 인터페이스를 구현한 class의 인스턴스 생성 후
		// 인스턴스를 Thread 객체 생성시 생성자의 파라미터 값으로 넘겨준다
		Runnable r = new MyRunnable();
		Thread th2 = new Thread(r);
		th2.start();

		// 3. 익명 클래스 이용
		// Runnable 인터페이스로 구현한 익명클래스를 Thread 인스터스를 생성할 때 파라미터 값으로 전송
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("&");
					try {
						// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드(ms) 단위를 사용한다.(1초 = 1000ms)
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		th3.run();

		System.out.println("메인스레드 작업 종료......");
	}
}

class MyThread1 extends Thread {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 중지
				// 시간은 ms 단위 사용 (1초 = 1000ms)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 중지
				// 시간은 ms 단위 사용 (1초 = 1000ms)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}