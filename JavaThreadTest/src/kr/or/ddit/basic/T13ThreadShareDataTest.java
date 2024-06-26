package kr.or.ddit.basic;

public class T13ThreadShareDataTest {
	public static void main(String[] args) {
		// 공유 객체 생성 (다른 Thread들과 공유하는 객체)
		ShareData sd = new ShareData();
		
		CalcPIThread cTh = new CalcPIThread(sd);
		PrintPIThread pTh = new PrintPIThread(sd);
		
		cTh.start();
		pTh.start();
		
				
	}
}

// 원주율 정보를 관리하기 위한 클래스 (공유 객체 생성용)
class ShareData {
	// 원주율이 저장될 변수
	private double result;

	// 원주율 게산이 완료되었는지 확인하기 위한 변수
	
//	 volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외
//	 				즉, 값이 변경되는 즉시 변수에  적용
//	  				멀티 스레드 환경에서 하나의 변수가 완벽하게 한번에 작동하도록 보장
//					(일종의 동기화)
	 
	volatile private boolean isOk;

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
}

class CalcPIThread extends Thread {
	private ShareData sd;

	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ...) * 4 
		 * 			1 - 3 + 5 - 7 + 9 ... => 분모 
		 * 			0 	1 	2 	3 	4 ... => 2로 나눈몫
		 */

		double sum = 0.0;
		for (int i = 1; i < 1500000000; i += 2) {
			// 2로 나눈 몫이 짝수이면 부호가 +
			if (((i / 2) % 2) == 0) {
				sum += (1.0 / i);
			}
			// 아니면 부호가 0
			else {
				sum -= (1.0 / i);
			}
		}

		// 계산된 원주율 설정
		sd.setResult(sum * 4);

		// 원주율 계산 완료됨을 알려줌
		sd.setOk(true);
	}
}

// 계산된 원주율을 출력
class PrintPIThread extends Thread {
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		while (true) {
			// 원주율이 계산되었는지 확인
			if (sd.isOk()) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.getResult());
		System.out.println(" 	   PI : " + Math.PI);
	}
}