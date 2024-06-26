package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeWork05 {
	static int RANK = 1;

	public static void main(String[] args) {
		List<Line> LineList = new ArrayList<Line>();

		for (int i = 1; i <= 10; i++) {
			LineList.add(new Line(i + "번말"));
		}

		for (Line hs : LineList) {
			hs.start();
		}

		while (HomeWork05.RANK <= 10) {
			for (Line hs : LineList) {
				System.out.println(hs.getStr());
			}
			System.out.println();
			System.out.println();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Line h : LineList) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(LineList);
		System.out.println();
		System.out.println("========================================");
		System.out.println("순위\t:\t말번호");
		System.out.println("========================================");
		for (Line dc : LineList) {
			System.out.println(dc.getRank() + "\t:\t" + dc.getName());
		}
	}
}

class Line extends Thread implements Comparable<Line> {
	private String name;
	private int rank;
	private String str = "";

	public Line(String name) {
		super(name);
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public void run() {

		for (int i = 1; i < 50; i++) {
			String strIng = name + "\t";
			for (int j = 1; j < i; j++) {
				strIng += "=";
			}
			strIng += ">";

			for (int j = i; j <= 50; j++) {
				strIng += "-";
			}

			setStr(strIng);

			try {
				Thread.sleep((int) (Math.random() * 500 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(HomeWork05.RANK++);
	}

	@Override
	public int compareTo(Line o) {
		return new Integer(this.rank).compareTo(o.rank);
	}

}
