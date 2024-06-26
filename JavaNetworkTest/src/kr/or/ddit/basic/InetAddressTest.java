package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddresss클래스 => IP 주소 정보를 다루기 위한 클래스

		// getByName()은 www.naver.com OR PC22등과 같은 머신이름이나
		// IP주소를 통해 유호한 InetAddress 객체를 제공
		// IP주소 자체를 넣으면 주소 구성 자체의 유성 정도만 체크

		// 네이버 사이트의 IP정보 가져오기
		InetAddress naverIP = InetAddress.getByName("192.168.36.128");
		System.out.println("Host Name => " + naverIP.getHostName());
		System.out.println("Host Addresss => " + naverIP.getHostAddress());
		System.out.println();

		// 자기 자신의 IP주소 정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " + localIp.getHostAddress());

		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for (InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
	}
}
