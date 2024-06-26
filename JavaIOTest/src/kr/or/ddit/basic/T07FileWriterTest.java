package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) throws IOException {
		// 사용자가 입력한 내용을 그대로 파일로 저장

		// Console가 연결된 입력용 문자 스트림 객체 생성
		// InputStreamReader => 바이트 기반 스트림을 문자기반 스트림으로
//		 						변환해 주기 위한 보조 스트림 (=한국어 가능)
		InputStreamReader isr = new InputStreamReader(System.in);

		FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");

		int data = 0;

		System.out.println("아무거나 입력하세요.");

		// Console 입력할 떄 입력의 끝(EOF)을 나타내기 위해서는 Ctrl+Z
		while ((data = isr.read()) != -1) {
			fw.write(data);
		}
		System.out.println("출려 작업 끝...");
		isr.close();
		fw.close();
	}
}
