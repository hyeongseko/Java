package kr.or.ddit.basic;

/*
	 열거형 => 상수값들을 선언하는 방법
	 선언 방법 : enum 열거형 이름 {상수값 1, 상수값 2, ......., 상수값 n};
 */

public class T07EnumTest {
	// City 열거형 객체 선언 (기본값을 이용하는 열거형)
	public enum City {
		서울, 부산, 대구, 광주, 대전
	};

	// 데이터값을 임의 지정한 열거형 객체 선언
	// 데이터값을 정해줄 경우에는 생성자를 이용하여 제공해 주면 된다.

	public enum Season {
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");

		// 괄호안의 값이 저장될 변수 선언
		private String data;

		// 생성자 만들기 (열거형의 생성자는 제어자가 묵시적으로 'private'이다)
		private Season(String data) {
			this.data = data;
		}

		// getter 메서드
		public String getData() {
			return data;
		}
	}

	public static void main(String[] args) {
		/*
		 * 열거형에서 사용하는 주요 메서드
		 * 
		 * 1. name()=>열거형 상수의 이름을 문자열로 반환
		 * 
		 * 2. valueOf("열거형 상수이름") => '열거형 상수이름'과 일치하는 열거형 상수 객체를 반환
		 * 
		 * 3. ordinal() => 열거형 상수가 정의된 순서값을 반환 (기본적으로 0부터 시작)
		 */

		City myCity1; // 열거형 객체변수 선언
		City myCity2; // 열거형 객체변수 선언

		myCity1 = City.서울;
		myCity2 = City.valueOf("서울");

		System.out.println("myCity1 : " + myCity1.name());
		System.out.println("myCity1의 ordinal : " + myCity1.ordinal());
		System.out.println();

		System.out.println("myCity2 : " + myCity2.name());
		System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		System.out.println("================================================================");

//		Season ss = Season.여름;
		Season ss = Season.valueOf("여름");

		System.out.println("name => " + ss.name());
		System.out.println("oridnal => " + ss.ordinal());
		System.out.println("getter메서드 호출값 => " + ss.getData());
		System.out.println("-----------------------------------------------------------------");

		// 열거형이름.values() => 열거형 배열값을 반환
		Season[] ssArr = Season.values();
		for (Season s : ssArr) {
			System.out.println(s.name() + " : " + s.getData());
		}
		System.out.println();

		for (City city : City.values()) {
			System.out.println(city.name() + " : " + city.ordinal());
		}

		City city = City.대구;
		System.out.println(city == City.대구);
		System.out.println(city == City.대전);

		////////////////////////////////////////////////////////////////

		System.out.println("대구 => " + city.compareTo(City.대구));
		System.out.println("서울 => " + city.compareTo(City.서울));
		System.out.println("대전 => " + city.compareTo(City.대전));
	}
}
