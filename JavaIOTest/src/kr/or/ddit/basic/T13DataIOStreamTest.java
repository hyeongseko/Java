// 기본타입 입출력 보조 스트림 예제
package kr.or.ddit.basic;

import java.io.*;

public class T13DataIOStreamTest {
	public static void main(String[] args) throws IOException {

		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");

		// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력
		DataOutputStream dos = new DataOutputStream(fos);

		// 문자열 데이터 출력(UTF-8)
		dos.writeUTF("홍길동");
		// 정수형 데이터 출력
		dos.writeInt(17);
		// 실수형(double) 데이터 출력
		dos.writeDouble(3.14);
		// 실수형(float) 데이터 출력
		dos.writeFloat(3.14f);
		// 논리형 데이터 출력
		dos.writeBoolean(true);

		dos.close();

		System.out.println("데이터 출력 완료...");

		///////////////////////////////////////////////////////////////////////
		// 출력한 데이터 읽어오기
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		// 저장한 순서대로 출력
		System.out.println("문자열 데이터 : " + dis.readUTF());
		System.out.println("정수형 데이터 : " + dis.readInt());
		System.out.println("실수형(Double) 데이터 : " + dis.readDouble());
		System.out.println("실수형(Float) 데이터 : " + dis.readFloat());
		System.out.println("논리형 데이터 : " + dis.readBoolean());

		dis.close();
	}
}
