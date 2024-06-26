package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {
	private DatagramSocket ds;
	private DatagramPacket dp;

	private byte[] msg;

	public UdpClient() {
		msg = new byte[100];
		try {
			ds = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public void start() throws IOException {
		InetAddress serverAddr = InetAddress.getByName("192.168.36.131");

		// 패킷 전송
		dp = new DatagramPacket(msg, 1, serverAddr, 8888);
		ds.send(dp);

		// 패킷 수신
		dp = new DatagramPacket(msg, msg.length);
		ds.receive(dp);

		System.out.println("현재 서버시간 => " + new String(dp.getData()));
	}

	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
}
