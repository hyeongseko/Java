package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpSocketClientTest {
	public static void main(String[] args) throws IOException {
		String serverIp = "127.0.0.1";
		// 자기자신 호스트를 나타내는 방법
		// IP : 127.0.0.1
		// 도메인명 : localhost

		System.out.println(serverIp + " 서버에 접속중입니다.");

		// 소켓을 설정해서 서버에 연결 요청
		Socket socket = new Socket(serverIp, 7777);

		System.out.println("서버에 연결되었습니다.");

		// 서버에서 보내온 메세지
		DataInputStream dis = new DataInputStream(socket.getInputStream());

		System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());

		System.out.println("클라이언트 연결 종료....");

		dis.close();

		socket.close();
	}
}
