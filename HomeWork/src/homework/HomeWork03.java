package homework;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HomeWork03 {
	private Scanner scan;
	private Map<Integer, RoomVo> hotelmap;

	public HomeWork03() {
		scan = new Scanner(System.in);
		hotelmap = new HashMap<Integer, RoomVo>();
	}

	public static void main(String[] args) {
		new HomeWork03().hotelStart();
	}
  
	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println();
		System.out.println("**************************");
		System.out.println("\t호텔 문을 열었습니다.");
		System.out.println("\t어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
	}

	// 프로그램을 시작하는 메서드
	public void hotelStart() {

		while (true) {
			displayMenu();
			int menuNum = scan.nextInt(); 
			switch (menuNum) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				displayAll();
				break;
			case 4:
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} 
		} 
	}

	// 객실 정보 전체 출력
	private void displayAll() {

		Set<Integer> keySet = hotelmap.keySet();
		System.out.println();
		System.out.println("------------------------------------------");
		if (keySet.size() == 0) {
			System.out.println("등록된 객실이 없습니다");
		} else {
			Iterator<Integer> it = keySet.iterator();
			while (it.hasNext()) {
				Integer num = it.next();
				RoomVo p = hotelmap.get(num);
				System.out.println("방번호 : " + p.getNum() + ", 투숙객 : " + p.getName());
			}
		}
		System.out.println("------------------------------------------");
	}

	// 체크아웃
	private void delete() {

		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int num = scan.nextInt();

		if (hotelmap.remove(num) == null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}

	}

	// 객실정보 등록
	private void insert() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int num = scan.nextInt();

		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		
		if (hotelmap.get(num) != null) {
			System.out.println(num + "방에는 이미 사람이 있습니다.");
			return;
		}

		hotelmap.put(num, new RoomVo(num, name));
		System.out.println("체크인 되었습니다.");
	}

}

class RoomVo {
	private int num;
	private String name;

	public RoomVo(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PhoneVO [num=" + num + ", name=" + name + "]";
	}

}
