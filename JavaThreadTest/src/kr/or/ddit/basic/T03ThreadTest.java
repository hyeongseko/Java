package kr.or.ddit.basic;

public class T03ThreadTest {
	public static void main(String[] args) {
		
		Thread th = new Thread(new MyRunner());

//		UTC를 사용하여 1970년 1월1일 00:00:00을 기준으로
//		경과한 시간을 밀리세컨드 단위로 표현
		long startTime = System.currentTimeMillis();

		// Thread start
		th.start();

//			현재 실행중인 Thread에서 
//			작업중인 Thread가 종료될때까지 대기
 		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (endTime - startTime));
	}
}

//1~100000000
class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for (int i = 1; i <= 1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}
