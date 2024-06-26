package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket ds;
	private DatagramPacket dp;

	private byte[] msg;

	public UdpServer(int port) {
		// 메시지 수신을 위한 포트번호 설정
		try {
			ds = new DatagramSocket(8888);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		while (true) {
			// 데이터 수신하기 위한 패킷객체를 생성
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);

			System.out.println("패킷 수신 대기중...");

			// 패킷 객체를 통해 데이터를 수신
			ds.receive(dp);

			System.out.println("패킷 수신 완료...");

			// 수신한 패킷정보를 확인하여 보낸사람의 IP주소와 포트번호를 알아낸다.
			InetAddress ipAddr = dp.getAddress();
			int port = dp.getPort();

			// 서버의 현재 시간을 시분초 형태 문자열로 전송
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); // 시간문자열을 바이트배열로 반환

			// 패킷에 바이트배열 데이터를 담아 상대방에게 전송
			dp = new DatagramPacket(msg, msg.length, ipAddr, port);
			ds.send(dp);
		}
	}

	public static void main(String[] args) throws Exception {
		new UdpServer(8888).start();
	}
}
