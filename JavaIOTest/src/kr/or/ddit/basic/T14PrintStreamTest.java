package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class T14PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/PrintStream/print.txt");

		// PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공
		// OutputStream의 서브 클래스
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream 입니다.\n");
		out.println("안녕하세요. PrintStream 입니다.2");
		out.println("안녕하세요. PrintStream 입니다.3");
		// 객체 출력
		out.println(out);
		// double 데이터 출력
		out.println(3.14);

		out.close();

		///////////////////////////////////////////////////////////////////////

		
//		PrintWriter는 데이터를 문자로 출력하는 기능 제공 
//		PrintStream보다 향상된 기능을 제고하지만 계속
//		PrintStream이 사용 PrintWriter는 다양한 인코딩 처리를 하는데 적합한 스트림 클랙스
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/PrintStream/print2.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos2,"CP949");
		PrintWriter pw = new PrintWriter(osw);
		pw.print("안녕하세요. PrintStream 입니다.\n");
		pw.println("안녕하세요. PrintStream 입니다.2");
		pw.println("안녕하세요. PrintStream 입니다.3");
		pw.println(out);
		pw.println(3.14);

		pw.close();
		
		System.out.println("작업 끝....");
	}
}
