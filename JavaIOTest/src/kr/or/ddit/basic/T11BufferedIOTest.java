package kr.or.ddit.basic;

import java.io.*;

public class T11BufferedIOTest {
	public static void main(String[] args) throws IOException {

		// 입출력 성능향상을 위해 buffer를 이용하는 보조스트림
		FileOutputStream fos = new FileOutputStream("d:/D_Other/bufferTest.txt");

		// buffer 크기를 지정하지 않으면 기본적으로 buffer 크기는 8192byte(8KB)로 설정
		// buffer 크기가 5byte인 보조스트림 객체 생성
		BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
		
		
		// fos : 매번 한번씩 호출하여 파일을 저장하여 시간이 오래걸림
		// bos : buffer를 사용하여 한방에 파일을 저장하도록 노력
		for (char ch = '1'; ch <= '9'; ch++) {
			bos.write(ch);
		}
		
		// 작업을 종료하기전에 버퍼에 남아있는 데이터를 모두 출력
		// close() 호출시 자동으로 호출
		bos.flush();
		bos.close();
		System.out.println("쓰기 작업 끝...");
	}
}
