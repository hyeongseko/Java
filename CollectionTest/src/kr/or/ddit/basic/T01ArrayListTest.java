package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {
	public static void main(String[] args) {
		// Defauly Capacity = 10
		List list1 = new ArrayList();

		// add() 메서드를 사용하여 데이터 추가
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111); // = list1.add(new Integer(111));
		list1.add("k");
		list1.add(true);
		list1.add("12.34");
		System.out.println("size => " + list1.size());
		System.out.println("List1 => " + list1);

		// get()으로 데이터 가져오기
		System.out.println("1번쨰 데이터 : " + list1.get(0));

		// 데이터 끼워넣기
		list1.add(0, "zzz");
		System.out.println("zzz 끼워넣기 후 : " + list1);

		// 데이터 변경하기
		String temp = (String) list1.set(0, "YYY");
		System.out.println("temp : " + temp);
		System.out.println("첫번째 데이터를 YYY로 변경한 후 : " + list1);

		// 데이터 삭제
		list1.remove(0);
		System.out.println("첫번쨰 데이터 삭제후 : " + list1);

		list1.remove("bbb");
		System.out.println("bbb 데이터 삭제후 : " + list1);

		list1.remove(new Integer(111));
		System.out.println("111 삭제 이후 : " + list1);
		System.out.println("=======================================");

		// 제네릭을 사용하여 선언
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");

		for (String str : list2) {
			System.out.println(str);
		}
		System.out.println("--------------------------------------");

		// contatins(비교객체) => 리스트에 '비교객체'가 있으면 true, 없으면 false
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));

		// indexOf(배교객체) => 리스트에 '비교객체'를 찾아 존재하는 인덱스(위치) 값으로 반환, 존재하지 않으면 -1 반환
		System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("---------------------------------------");

		// 모든 데이터 삭제
		for (int i = list2.size() - 1; i >= 0; i--) {
			list2.remove(i);
		}
		System.out.println("list2의 개수 : " + list2.size());
	}
}
