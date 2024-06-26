package kr.or.ddit.basic;

public class T14SyncThreadtest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();

		WorkThread th1 = new WorkThread("1번Thread", sObj);
		WorkThread th2 = new WorkThread("2번Thread", sObj);

		th1.start();
		th2.start();
	}
}

// 공유객체
class ShareObject {
	private int sum = 0;

//	 동기화 방법 1 : 메서드 자체에 동기화 설정
//	synchronized public void add() {
	public void add() {

//		동기화 방법 2 : 동기화 블럭으로 설정하기 1
//		MUTEX : Mutual Exclusion Object (상호 배제 : 동시접근 차단)
		synchronized (this) {
			for (int i = 0; i < 1000000000; i++) {
			}

			int n = sum;
			n += 10;
			sum = n;
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}

// 작업 수행하기 위한 Thread
class WorkThread extends Thread {
	private ShareObject sObj;

	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
//			동기화 방법 2 : 동기화 블럭으로 설정하기 2
			synchronized (sObj) {
				sObj.add();
			}
		}
	}
}
