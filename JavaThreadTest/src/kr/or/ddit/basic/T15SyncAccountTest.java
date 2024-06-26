package kr.or.ddit.basic;

public class T15SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(25000);
		for (int i = 0; i < 3; i++) {
			BankThread bThi = new BankThread(sAcc);
			bThi.start();
		}
	}
}

// 은행의 입출금 관리
class SyncAccount {
	private int balance;

	public int getBalance() {
		return balance;
	}

	// 입급 처리를 수행
	public void deposit(int money) {
		balance += money;
	}

	// 출금 처리 (출금 성공 : true, 출금 실패 : false)
	// 동기화 역역에서 호출되는 메서드도 동기화 처리를 해주어야한다
	public boolean withdraw(int money) {
		synchronized (this) {
			if (balance >= money) {
				for (int i = 1; i <= 1000000000; i++) {
				}

				// 출금 작업
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				return true;
			} else {
				return false;
			}
		}
	}
}

// 은행 업무 처리
class BankThread extends Thread {
	private SyncAccount sAcc;

	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}

	@Override
	public void run() {
		// 6000원 인출
		boolean result = sAcc.withdraw(6000);
		System.out.println("Thread Result = " + result + ", balance = " + sAcc.getBalance());
	}
}