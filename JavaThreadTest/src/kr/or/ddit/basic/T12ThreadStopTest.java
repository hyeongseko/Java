/*
 * Thread 종료 예제
 * */
package kr.or.ddit.basic;

public class T12ThreadStopTest {
	public static void main(String[] args) {

//		ThreadStopEx1 th1 = new ThreadStopEx1();
//		th1.start();
//
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// interrupt 걸어서 종료
		th2.interrupt();
		
		
//		// Thread 종료
//		// th1.stop(); // 강제종료 버튼 : 사용하지마삼 (사람의 본성 확인)
//		th1.setStoped(true);

	}
}

class ThreadStopEx1 extends Thread {
	private boolean isStoped;

	public boolean isStoped() {
		return isStoped;
	}

	public void setStoped(boolean isStoped) {
		this.isStoped = isStoped;
	}

	@Override
	public void run() {
		while (!isStoped) {
			System.out.println("Thread 처리 중 ...");
		}
		System.out.println("자원 정리 중........... ");
		System.out.println("실행 종료.");
	}
}

class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
//		1. sleeop()이나 join()을 사용할 때 interrupt()를 호출하면
//			InterrupedException 발생
//		 	이 예외를 이용
//		try {
//			while (true) {
//				System.out.println("쓰레드 처리 중 ....");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException ex) {
//		}

//		2. interrupt 호출되었는지 검사
		while(true) {
			System.out.println("쓰레드 처리중....");
			
//			1) Thread instace() 이용
//			if(this.isInterrupted()) {
//				System.out.println("isInterrupted() 호출");
//				break;
//			}
			
			
//			2) Thread 정적 메서드 이용
			if(Thread.interrupted()) {
				System.out.println("정적메서드 interrupted() 호출");
				break;
			}
		}		
		
		System.out.println("자원 정리 중 ...");
		System.out.println("실행 종료.");

	}
}