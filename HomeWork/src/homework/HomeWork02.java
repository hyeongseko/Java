package homework;


import java.util.Scanner;
import java.util.TreeSet;

public class HomeWork02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int sel = sc.nextInt();
			if (sel == 1) {
				System.out.println();
				System.out.println("Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int count = sc.nextInt();
				int a = count / 1000;
				int b = count % 1000;

				System.out.println();
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				for (int i = 1; i <= a; i++) {
					TreeSet<Integer> lotto = new TreeSet<Integer>();
					while (lotto.size() < 6) {	
						int num = (int) (Math.random() * 45 +  1);
						lotto.add(num);
					}
//					Hashset 사용하여 정렬
//					List<Integer> num = new ArrayList<Integer>(lotto);
//					Collections.sort(num);
					System.out.println("로또번호" + i + " : " + lotto);
				}
				System.out.println();
				System.out.println("받은금액은" + count + "원이고 거스름돈은" + b + "원입니다.");
				System.out.println();
			}
			if (sel == 2) {
				System.out.println("감사합니다");
				break;
			}
		}
	}
}
