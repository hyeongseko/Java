package kr.or.ddit.reflect;

/*
 * Java Reflection 에 대하여...
 * 	1. Reflection은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 자겨오거나 수정 가능,
 * 		새로운 객체를 생성하거나 메서드를 실행 가능
 * 			(컴파일 시점에 해당 정보를 알 수 없는 경우(소스코드 부재)에 유용할게 사용)
 * 	2. Relection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공
 * 	3. java.lang.Class의 주요 메서드
 * 		- getName(), getSuperclass(), getInterfaces(), getModifiers() 등.
 * 	4. java.lang.reflect 패키지의 주요 클래스
 * 		- Field, Method, Constructor, Modifier 등.
 */
public class T01ClassObjectCreationTest {
	public static void main(String[] args) throws ClassNotFoundException {
		// Class 객체 생성
		// 1. Class.forName()
		Class<?> klass = Class.forName("kr.or.ddit.reflect.T01ClassObjectCreationTest");
		
		// 2. GetClass
		T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
		klass = obj.getClass();
		
		// 3. .class
		klass = T01ClassObjectCreationTest.class;
	}
}
