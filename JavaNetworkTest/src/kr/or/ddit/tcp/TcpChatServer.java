package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(7777);

			System.out.println("채팅서버 준비완료...");

			socket = serverSocket.accept();
			
			System.out.println("클라이언트 점속 성공");

			// 메세지 전송용 Thread 생성 및 실행
			Sender sender = new Sender(socket);
			sender.start();

			// 메세지 수신용 Thread 생성 및 실행
			Receiver receiver = new Receiver(socket);
			receiver.start();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
