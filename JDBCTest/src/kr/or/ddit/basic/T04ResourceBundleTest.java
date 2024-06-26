package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 ResourceBundle 객체를 이용하여 properties 파일 읽어오기
 ResourceBundle 객체 생성시 파일명을 지정할 때 확장자명은 지정하지 않는다
 	(확장자명은 항상 .properties)
*/
public class T04ResourceBundleTest {
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.JAPANESE);
		
		// key 값들만 읽어오기　
		Enumeration<String> keys = bundle.getKeys();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = bundle.getString(key);

			System.out.println(key + " : " + value);
		}
		System.out.println("출력 끝...");
	}
}
