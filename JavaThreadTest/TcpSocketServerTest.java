package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServerTest {
	public static void main(String[] args) throws IOException {
		// TCP 소켓 통신을 위한 서버소켓 생성
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 기다립니다...");

		// accept()메소드는 클라이언트에서 연결요청이 올 때까지 계속 기다린다.
		// 연결 요청이 오면 Socket 객체를 생성해서 클라이언트의 Socket과 연결
		Socket socket = server.accept();

		////////////////////////////////////////////////////
		// 이 이후는 크아이언트와 연결된 후의 작업을 진행

		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : " + socket.getInetAddress());

		// 클라이언트에게 메세지 보내기
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF("어서오세요....");

		System.out.println("클라리언트에게 메세지를 보냈습니다.");

		dos.close();

	}
}
