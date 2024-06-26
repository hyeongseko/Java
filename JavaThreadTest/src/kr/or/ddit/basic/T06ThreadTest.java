package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06ThreadTest {

	public static boolean INPUT_CHECK = false;

	public static void main(String[] args) {
		Thread th1 = new DataInput();
		th1.start();

		Thread th2 = new CountDown();
		th2.start();
	}
}

// 사용자 입력을 처리하는 Thread
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		// 입력이 완료
		T06ThreadTest.INPUT_CHECK = true;
		System.out.println("입력한 값은 " + str + "입니다.");
	}
}

class CountDown extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			// 사용자 입력 여부 확인, 완료된 경우 카운트 다운 종료

			if (T06ThreadTest.INPUT_CHECK) {
				return; // run()메서드 종료
			}
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 10초 경과 후 입력이 없으면 종료
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다");
		System.exit(0);
	}
}