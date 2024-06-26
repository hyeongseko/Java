package kr.or.ddit.basic;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.36.128",7777);
		
		System.out.println("채팅서버에 연결되었습니다....");
		
		Sender sender = new Sender(socket);
		sender.start();
		
		Receiver receiver = new Receiver(socket);
		receiver.start();
	}
}
