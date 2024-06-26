package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		// 직접 복사
//		outSrc = new byte[inSrc.length];
//		for (int i = 0; i < inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
//		System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
//		
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
//		
//		System.out.println("arraycopy 복사 후 outSrc\n => " + Arrays.toString(outSrc));

		// String Class 이용
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int data = 0;

		// 복사가 끝나면 while문 종료
		while ((data = bais.read()) != -1) {
			baos.write(data);
		}
		
		outSrc = baos.toByteArray();
		System.out.println("Stream Class 후 outSrc\n => " + Arrays.toString(outSrc));

	}
}
