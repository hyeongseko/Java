/*
	Vector, Hastable 등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어있다
	하지만, 최근에 새로 구성된 Collection들은 동기화 처리가 되어 있지 않다.
	그래서 동기화가 필요한 경우에는 직접 동기화 처리를 해서 사용
*/

package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T16SyncCollectionTest {
	// 동기화 처리를 하지 않을 경우
	private static List<Integer> List1 = new ArrayList<Integer>();
	
	// 동기화 처리를 했을 경우
	private static List<Integer> List2 = java.util.Collections.synchronizedList(new ArrayList<Integer>());

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
//					List1.add(i);
					List2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] { new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r), };
		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println("List의 개수 : " + List1.size());
		System.out.println("List의 개수 : " + List2.size());
	}
}
