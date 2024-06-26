package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeWork01 {
	public static void main(String[] args) {
		List<Student> stdlist = new ArrayList<Student>();

		stdlist.add(new Student("2018036039", "홍길동", 95, 80, 60));
		stdlist.add(new Student("2018036038", "변학도", 80, 75, 80));
		stdlist.add(new Student("2018036036", "성춘향", 70, 75, 85));
		stdlist.add(new Student("2018036037", "이순신", 85, 75, 90));
		stdlist.add(new Student("2018036040", "강감찬", 85, 75, 95));
		stdlist.add(new Student("2018036041", "일지매", 65, 75, 85));
		stdlist.add(new Student("2018036042", "이혁도", 65, 75, 85));

		System.out.println("정렬 전 : ");
		for (Student std : stdlist) {
			System.out.println(std);
		}
		System.out.println("==================================================");

		Collections.sort(stdlist);
		System.out.println("학번의 오름차순 정렬 후  : ");
		for (Student std : stdlist) {
			System.out.println(std);
		}
		System.out.println("==================================================");

		int rank = 1;
		System.out.println("총점의 내림차순 정렬 후  : ");
		Collections.sort(stdlist, new SortNumDesc());
		for (Student std : stdlist) {
			std.setRank(rank++);
			System.out.println(std);
		}
		System.out.println("==================================================");

	}
}

// 총점의 내림차순 정렬
class SortNumDesc implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getSum() > std2.getSum()) { // 앞에 값이 더크면
			return -1;
		} else if (std1.getSum() == std2.getSum()) {
			return std1.getNum().compareTo(std2.getNum())*-1;
		} else {
			return 1;
		}
	}
}

class Student implements Comparable<Student> {
	private String num;
	private String name;
	private int korea;
	private int english;
	private int math;
	private int sum;
	private int rank;

	public Student(String num, String name, int korea, int english, int math) {
		super();
		this.num = num;
		this.name = name;
		this.korea = korea;
		this.english = english;
		this.math = math;
		this.sum = korea + english + math;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorea() {
		return korea;
	}

	public void setKorea(int korea) {
		this.korea = korea;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", korea=" + korea + ", english=" + english + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}

	//학번의 오름차순
	@Override
	public int compareTo(Student std) {
		return this.getNum().compareTo(std.num);
	}
}
