/*
	3개의 스레드가 각각 알파벳 대문자를 출력
	출력을 끝내는 순서대로 결과를 나타내는 프로그램 작성
 */
package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {

	static int CURR_RANK = 1;

	public static void main(String[] args) {
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		disCharList.add(new DisplayCharacter("김민강"));
		disCharList.add(new DisplayCharacter("이현수"));
		disCharList.add(new DisplayCharacter("이동형"));
		disCharList.add(new DisplayCharacter("이영준"));
		disCharList.add(new DisplayCharacter("김필거"));
		for (Thread th : disCharList) {
			th.start();
		}
		for (Thread th : disCharList)
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Collections.sort(disCharList);

		System.out.println("경긱끝 ...............................");
		System.out.println("-------------------------------------");
		System.out.println("경기 결과");
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println("순위\t : \t이름");
		System.out.println("---------------------------------------");

		for (DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank() + "\t : \t" + dc.getName());
		}

	}

}

// 알파벳 대문자를 출력하기 위한 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter> {

	private String name;

	private int rank;

	public DisplayCharacter(String name) {
		super(name);
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public void run() {

		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "" + "" + " 출력 문자 : " + ch);

			try {
				Thread.sleep((int) (Math.random() * 301 + 200)); // 200~500사이의 난수

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		setRank(T11DisplayCharacterTest.CURR_RANK++);

		System.out.println(name + " 출력 끝...");
	}

	@Override
	public int compareTo(DisplayCharacter dc) {
		// TODO Auto-generated method stub
		return new Integer(this.rank).compareTo(dc.rank);
	}

}