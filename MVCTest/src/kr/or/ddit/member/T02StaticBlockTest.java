package kr.or.ddit.member;

public class T02StaticBlockTest {
	public T02StaticBlockTest() {
		System.out.println("생성자 프출됨");
	}

	static {
		System.out.println("첫번째 static block 호출");
	}

	static {
		System.out.println("두번째 static block 호출");
	}

	{
		System.out.println("첫번째 instatic block 호출");
	}

	{
		System.out.println("두번째 instatic block 호출");
	}

	public static void maiin(String[] args) {
		new T02StaticBlockTest();
		new T02StaticBlockTest();
	}
}
