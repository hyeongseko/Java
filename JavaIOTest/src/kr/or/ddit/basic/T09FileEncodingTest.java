/*
한글 인코딩 방식...
	한글 인코딩 방식은 크게 UTF-8 과 EUC_KR방식
	원래 한글위도우는 CP949방식을 사용했는데, 
		Microsoft가 EUC_KR 방식을 확장만들어서 MS949라 부른다.
	CP949(Code Page 949) = 한글 윈도우의 메모장에서 말하는 ANSI 인코딩
							EUC_KR의 확장, 하위 호완성
	
	- MS949(CP040) => Window 기본 한글 인코딩방식 (ANSI 계역)
	- UTF-8=> 유니코드 UTF-8 인코딩 방식
	- US-ASCII => 영문 전용 인코딩 방식

참고)
ASCII => extended ASCII(ISP8859-1)-> 조합형, 완성형 
		=> 윈도우 계열 : CP949(확장완성형_ - 일부문자(8822자)추가
		=> 유닉스계열 : EUC_KR(확장 유닉스 코드)
		
	=> ANSI 계열
	
	=> 유니코드 (UTF-8)	
 */

package kr.or.ddit.basic;

import java.io.*;

public class T09FileEncodingTest {
	public static void main(String[] args) throws IOException {

//		FileInputStream fis = new FileInputStream("d:/D_Other/test_ansi.txt");
		FileInputStream fis = new FileInputStream("d:/D_Other/test_utf8.txt");

		// 파일 인코딩 정보를 이용하여 읽어오기
		// InputStreamReader를 이요하면 인코딩 방식을 지정하여 읽을 수 있다.
		// new InputStreamReader(바이트기반스트림객체, 인코딩방식)
//		InputStreamReader isr = new InputStreamReader(fis, "CP949");
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

		int data = 0;
		while ((data = isr.read()) != -1) {
			System.out.print((char) data);
		}
		
		isr.close();
		System.out.println();
		System.out.println("출력끝...");
	}
}
