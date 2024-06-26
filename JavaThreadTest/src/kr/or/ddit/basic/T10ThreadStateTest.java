package kr.or.ddit.basic;
/*
	Thread 상태에 대하여...	
	- NEW : Thread 생성되고 아직 start()가 호출되지 않은 상태
	- RUNABLE : 실행중 또는 실행 가능 상태
	- BLOCKED : 동기화블럭에 의해서 일시정지 상태 
				(Lock이 풀릴떄까지 대기 상태)
	- WAITING, TIMED_WAITING : Thread 작업이 종료되지는 않았지만
								실행 가능하지 않은 일시정지 상태
		 					   TIMED_WAITING은 일시정지 시간이 지정된 경우
	- TERMINATED : Thread 작업이 종료된 상태  
*/

public class T10ThreadStateTest {
	public static void main(String[] args) {
		Thread targetThread = new TargetThread();

		StatePrintThread spt = new StatePrintThread(targetThread);
		spt.start();
	}
}

// Thread 상태를 출력하기 위한 Thread
class StatePrintThread extends Thread {
	private Thread targetThread;

	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}

	@Override
	public void run() {
		while (true) {

			// Thread의 상태 구하기
			Thread.State state = targetThread.getState();
			System.out.println("Target Thread 상태값 : " + state);

			// NEW 상태인지 확인하여 구동
			if (state == Thread.State.NEW) {
				targetThread.start();
			}

			// Target Thread 종료상태인지 확인하여 무한루프 종료
			if (state == Thread.State.TERMINATED) {
				break;
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-geerated catch block
				e.printStackTrace();
			}
		}
	}
}

// Target용 Thread
class TargetThread extends Thread {
	@Override
	public void run() {
		// 시간 지연용
		for (long i = 1; i < 10000000000L; i++) {}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (long i = 1; i < 10000000000L; i++) {
		}
	}
}