/*
 	OutputStreamWriter => 바이트 기반 스트림 객체를 
 							문자기반 스트림 객체로 변환해 주는 보조 스트림
 						=> 이 스트림 객체도 '인코딩 방식'을 지정하여 출력
*/
package kr.or.ddit.basic;

import java.io.*;

public class T10FileEncodingTest {
	public static void main(String[] args) throws IOException {
		// 키보드로 입력한 내용을 파일로 저장
		// out_tf8.txt 파일은 'UTF-8' 인코딩 방식
		// out_ansi.txt 파일은 'MS949' 인코딩 방식

		InputStreamReader isr = new InputStreamReader(System.in);

		// 파일 출력용 객체 생성
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out-utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out-ansi.txt");

		// 보조스트림 객체 생성
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "MS949");
		
		System.out.println("아무거나 입력하세요");
		int data = 0 ;
		while((data = isr.read())!=-1) {
			osw1.write(data);
			osw2.write(data);
		}
		System.out.println("파일 출력 작업 완료...");
		isr.close();
		osw1.close();
		osw2.close();
	}
}
