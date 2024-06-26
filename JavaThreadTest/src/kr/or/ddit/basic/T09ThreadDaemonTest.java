package kr.or.ddit.basic;

public class T09ThreadDaemonTest {
	public static void main(String[] args) {

		AutoSaveThread autoTh = new AutoSaveThread();
		
		// DaemonThread = 일반 Thread를 보조해주는 Thread
		// DaemonThread 설정
		autoTh.setDaemon(true);
		autoTh.start();

		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println("작업 - " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("메인 스레드 종료....");
	}
}

// 자동 저장하는 기는을 제공하기 위한 Thread
class AutoSaveThread extends Thread {
	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			save();

		}
	}

	private void save() {
		System.out.println("작업 내용을 저장중입니다..........");
	}
}