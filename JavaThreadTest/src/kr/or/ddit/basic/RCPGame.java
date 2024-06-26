package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class RCPGame {
	public static boolean inputCheck = false;
	public static String man = "";

	public static void main(String[] args) {
		String[] data = { "가위", "바위", "보" };
		int index = (int) (Math.random() * 3); // 0~2사이의 난수 만들기
		String com = data[index];

		Input input = new Input();
		input.start();

		Timer timer = new Timer();
		timer.start();

		try {
			input.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = "";
		if (com.equals(man)) {
			result = "비겼습니다";
		} else if (man.equals("가위") && com.equals("보") || man.equals("주먹") && com.equals("가위")
				|| man.equals("보") && com.equals("주먹")) {
			result = "이겼습니다.";
		} else {
			result = "졌습니다.";
		}
		System.out.println("결과");
		System.out.println("컴퓨터 = " + com);
		System.out.println("사람 = " + man);
		System.out.println("결과 = " + result);
	}
}

class Timer extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (RCPGame.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("시간이 초과");
		System.exit(0);
	}
}

class Input extends Thread {
	@Override
	public void run() {
		String inputdata = "";
		do {
			inputdata = JOptionPane.showInputDialog("가위 바위 보");
		} while (!inputdata.equals("가위") && !inputdata.equals("바위") && !inputdata.equals("보"));
		RCPGame.inputCheck = true;
		RCPGame.man = inputdata;
	}
}