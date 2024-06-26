package kr.or.ddit.basic;

// @FunctionalInterface = 하나뿐인 Interface
@FunctionalInterface
public interface LambdaTestInterface1 {

	// 반환값이 없고, 매개변수도 없는 추상메서드 선언
	public void test();
}

@FunctionalInterface
interface LambdaTestInterface2 {

	// 반환값이 없고, 매개변수는 있는 추상메서드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3 {
	// 반환값과 매개변수다 있는 추상메서드 선언
	public int test(int a, int b);

}
