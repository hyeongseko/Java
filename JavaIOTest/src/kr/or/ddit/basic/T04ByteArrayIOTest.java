package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		byte[] temp = new byte[4];

		// String Class 이용
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// 읽어온 Byte 수
		int readBytesCnt = 0;

		// 복사가 끝나면 while문 종료
		while ((readBytesCnt = bais.read(temp)) != -1) {
			
			
			System.out.println("temp => " + Arrays.toString(temp));
			// 다 읽지 말고 readBytesCnt까지만
			// [0,1,2,3], [4,5,6,7], [8,9]
			baos.write(temp, 0, readBytesCnt);
		}

		outSrc = baos.toByteArray();
		System.out.println("Stream Class 후 outSrc\n => " + Arrays.toString(outSrc));

	}
}
