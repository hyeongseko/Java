package kr.or.ddit.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class T03MethodMetadataTest {
	// Class 오브젝트 생성
	public static void main(String[] args) throws ClassNotFoundException {

		Class<?> klass = Class.forName("kr.or.ddit.reflect.SampleVO");

		// 클래스에 선언된 모든 메서드 메타정보 가져오기
		Method[] methodArr = klass.getDeclaredMethods();

		for (Method m : methodArr) {
			System.out.println("메서드명 : " + m.getName());
			System.out.println("메서드 리턴타입 : " + m.getReturnType());

			// 해당 메서드의 접근제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메서드 접근제어자 : " + Modifier.toString(modFlag));

			// 해당 메서드의 파라미터 타입 가져오기
			Class<?>[] paramArr = m.getParameterTypes();
			System.out.println("메서드 파라미터 타입 : ");
			for (Class<?> clazz : paramArr) {
				System.out.print(clazz.getName() + " | ");
			}
			System.out.println();
			
			
			// 해당메서드에서 던지는 예외타입 가져오기
	         Class<?>[] exTypeArr = m.getExceptionTypes();
	         System.out.println("메서드에서 던지는 예외타입 목록: ");
	         for (Class<?> clazz : exTypeArr) {
	            System.out.println(clazz.getName() + " | ");
	         }
	         System.out.println();

			// 해당 메서드에 존재하는 Annotation 정보 불러오기
			Annotation[] annos = m.getDeclaredAnnotations();
			System.out.println("선언된 Annotion 타입 : ");
			for (Annotation anno : annos) {
				System.out.println(anno.annotationType().getName() + " | ");
			}
			System.out.println();
			System.out.println("-----------------------------------------------");
		}
	}
}
