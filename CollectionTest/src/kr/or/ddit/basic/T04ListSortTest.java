package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {
	public static void main(String[] args) {
		List<Member> memlist = new ArrayList<Member>();

		memlist.add(new Member(1, "홍길동", "010-5861-5043"));
		memlist.add(new Member(5, "변학도", "010-2221-5043"));
		memlist.add(new Member(9, "성춘향", "010-3331-5043"));
		memlist.add(new Member(3, "아슌신", "010-4444-5043"));
		memlist.add(new Member(6, "강감찬", "010-5555-5043"));
		memlist.add(new Member(2, "일지매", "010-6661-5043"));

		System.out.println("정렬 전 : ");
		for (Member mem : memlist) {
			System.out.println(mem);
		}
		System.out.println("============================");

		Collections.sort(memlist);

		System.out.println("이름의 오름차순 정렬 후  : ");
		for (Member mem : memlist) {
			System.out.println(mem);
		}
		System.out.println("============================");

		Collections.shuffle(memlist);

		System.out.println("썩은 후  : ");
		for (Member mem : memlist) {
			System.out.println(mem);
		}
		System.out.println("============================");

		Collections.sort(memlist, new SortNumDesc());

		System.out.println("번호의 내림차순 정렬 후  : ");
		for (Member mem : memlist) {
			System.out.println(mem);
		}
		System.out.println("============================");
	}
}

// 번호의 내림차순 정렬
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
//		if (mem1.getNum() > mem2.getNum()) { // 앞에 값이 더크면
//			return -1;
//		} else if (mem1.getNum() == mem2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}

//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
}

class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

//	이름을 기준으로 오름차순 정렬

	@Override
	public int compareTo(Member mem) {

		return this.getName().compareTo(mem.getName());
	}

}
