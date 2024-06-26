package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Annotation
 * 	프로그램 소스코드 안에 다른 포로그램을 위한 정보를 미리 약속된 형식으로 포함
 * 	주석처럼 프로그램에 영향을 미치지 않으면서, 다른 프로그램에 유용한 정보를 제공
 * 
 * 	종류 1 : 표준 애너테이션
 * 	종류 2 : 메타 애너테이션(에너테이션을 위한 에너테이션, 애너테이션을 정의할 때 사용하는 에너테이션
 * 
 * 	애너테이션 정의방법
 * 	@interface 애너테이션 이름{
 * 
 * 요소타입 요소이름(): 반환값이 있거, 매개변수는 없는 추상 메서드
 * 		....}
 *
 *
 *
 *
 *	애너테이션 정의할 때 주의할 점
 *		1. 요소타입은 기본형, String, enum, annotation, Class만 허용
 *		2. ()안에 매개변수를 ㄹ선언
 *		3. 예외를 선언할 수 없다.
 *		4. 요소타입에 타입 매개변수(재너릭타입글자)를 사용할 수 없다.
 *
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintAnnotation {
	int id = 100;
	String value() default "-";
	int count() default 20;
}
