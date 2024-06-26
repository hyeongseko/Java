package kr.or.ddit.basic;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class T02LambdaTest {
	public static void main(String[] args) {
		// 람다식을 사용하지 않을 경우
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {

			public void test() {
				System.out.println("안녕하세요");
				System.out.println("익명구현개체 방식입니다");
			}
		};
		lam1.test();

		LambdaTestInterface1 lam2 = () -> System.out.println("반갑습니다.\n람다식으로 구현하는 방식입니다.");

		lam2.test();
		System.out.println("--------------------------------------------");

//	람다식 작성 방법   	
//  	기본형식) (자료형 이름 매개변수명, ...) -> {실행문들; ...}  	
//		
//  	1) 매개변수의 '자료형이름'은 생략 가능
//		Ex) (int a) -> { System.out.println(a);}
//				(a) -> { System.out.println(a);}
//		
//		2) 매개변수가 1개일 경우에는 '()' 생략 가능
//			(단, '자료형이름'을 지정할 경우에는 괄호 생략 가능)
//		Ex) a -> { System.out.println(a);
//		
//		3) '실행문'이 1개일 경우에는 '{}' 생략 가능
//			(이 때 문장의 끝을 나타내는 ';'도 생략 가능)
//		Ex) a -> System.out.prinln(a)
//		
//		4) 매개변수가 하나도 없으면 '()' 생략 불가
//		Ex) () -> System.out.println("안녕")
//		
//		5) 반환값이 있을 경우에는 return 명령 사용
//		Ex) (a, b) -> { return a + b; }
//			(a, b) -> return a + b;
//		
//		6) 실행문에 return문만 있을경우 return, '{}' 생략 가능
//		Ex) (a,b) -> a + b

		Consumer<Integer> lam3 = (Integer z) -> {
			int result = z + 100;
			System.out.println("result = " + result);
		};
		lam3.accept(30);

		Consumer<Integer> lam4 = z -> {
			int result = z + 300;
			System.out.println("result = " + result);
		};
		lam4.accept(60);

		Consumer<Integer> lam5 = z -> System.out.println("result = " + (z + 500));
		;
		lam5.accept(90);
		System.out.println("--------------------------------------");

		BiFunction<Integer,Integer,Integer> lam6 = (Integer x, Integer y) -> {
			int r = x + y;
			return r;
		};
		int k = lam6.apply(20, 50);
		System.out.println("k = " + k);

		BiFunction<Integer,Integer,Integer> lam7 = (Integer x, Integer y) -> {
			return x + y;
		};
		k = lam7.apply(80, 50);
		System.out.println("k = " + k);

		BiFunction<Integer,Integer,Integer> lam8 = (x, y) -> x + y;
		k = lam8.apply(100, 200);
		System.out.println("k = " + k);

		BiFunction<Integer,Integer,Integer> lam9 = (x, y) -> x > y ? x : y;
		k = lam9.apply(100, 200);
		System.out.println("k = " + k);
	}
}
