/*
 * 람다식 => 익명함수를 생성하기 위한 식, 자바에서는 '매개변수를 가진 코드 블럭'
 * 			런타임시 => 익명구현객체로 생성
 * 사용 예) 인터페이스명 객체변수명 = 람다식;
 * 
 * 람다식의 형태 : (매개변수들...) -> {처리할 코드들;...}
 * 
 * => 람다식으로 변환할 수 있는 익명 구현객체는 추상메서드가 1개인 인터페이스인 경우에만 가능
 * 		이러한 인터페이스를 '함수적 인터페이스(Functional Interface)' 라고 부른다
 */
package kr.or.ddit.basic;

public class T01LambdaTest {
	public static void main(String[] args) {

		// 람다식 사용을 하지 않는 경우
		Thread th1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					System.out.println("th-" + i);
				}
			}
		});
		th1.start();

		Thread th2 = new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				System.out.println("람다-" + i);

			}
		});
		System.out.println();
		th2.start();
	}
}
