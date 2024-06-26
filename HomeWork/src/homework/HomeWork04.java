package homework;

import java.util.Scanner;

public class HomeWork04 {

	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double data;

		private Planet(double data) {
			this.data = data;
		}

		public double getData() {
			return data;
		}
	}

	public static void main(String[] args) {

		Planet[] ssArr = Planet.values();
		for (Planet s : ssArr) {
			System.out.println(s.name() + " : " + 4 * Math.PI * s.getData() * s.getData());
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------");
		System.out.println();
		System.out.print("원하는 행성을 입력하세요 : ");
		String input = sc.next();
		Planet ss = Planet.valueOf(input);
		System.out.println("행성의 이름 => " + ss.name());
		System.out.println("oridnal => " + ss.ordinal());
		System.out.println("행성의 면적 => " + 4 * 3.14 * ss.getData() * ss.getData());

	}
}
