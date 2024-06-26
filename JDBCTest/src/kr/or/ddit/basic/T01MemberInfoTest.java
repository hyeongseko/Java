package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.or.ddit.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);
*/
public class T01MemberInfoTest {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
	private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 자료 입력
				insertMember();
				break;
			case 2: // 자료 삭제
				delteMember();
				break;
			case 3: // 자료 수정
				updateMember();
				break;
			case 4: // 전체 자료 출력
				displayAllMember();
				break;
			case 5: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 5);
	}

	// 모든 회원정보 출력
	private void displayAllMember() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println(" ID\t생성일\t\t이름\t전화번호\t\t주소");
		System.out.println("---------------------------------------------------");

		try {

			conn = JDBCUtil3.getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(" select * from mymember ");

			while (rs.next()) {
				String memId = rs.getNString("mem_id");
				String memName = rs.getNString("mem_name");
				String memTel = rs.getNString("mem_tel");
				String memAddr = rs.getNString("mem_addr");

				LocalDate regDt = rs.getTimestamp("reg_dt").toLocalDateTime().toLocalDate();

				System.out.println(memId + "\t" + regDt + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("---------------------------------------------------");
			System.out.println("출력 끝....");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

	}

	// 회원 삭제
	private void delteMember() {

		System.out.println();
		System.out.println("수정 할 회원 정보를 입력해 주세요.");
		System.out.print("회원 ID >> ");
		String memId = scan.next();

		//////////////////////////////////////////////////////
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " delete from mymember where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memId + "인 회원정보 변경 성공");
			} else {
				System.out.println(memId + "인 회원정보 변경 실패!!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	// 회원 정보 변경
	private void updateMember() {
		boolean isExist = false;
		String memId = "";
		do {
			System.out.println();
			System.out.println("수정 할 회원 정보를 입력해 주세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next();

			isExist = checkMember(memId);

			if (!isExist) {
				System.out.println("회원ID가" + memId + "인 회원이 없습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		} while (!isExist);

		System.out.print("회원 이름 >> ");
		String memName = scan.next();

		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine();

		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();

		////////////////////////////////////////////////////////////////////////

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc22_00001", "java");
			String sql = " update mymember\r\n" + "set mem_name=?, mem_tel=?, mem_addr=? where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memId + "인 회원정보 변경 성공");
			} else {
				System.out.println(memId + "인 회원정보 변경 실패!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 close = finally 넣는이유 반드시 실행해야하기때문에
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	// 회원정보 등록
	private void insertMember() {
		boolean isExist = false;
		String memId = "";
		// 처음 실행후 중복 아이디가 없을때까지 실행
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해 주세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next();

			isExist = checkMember(memId);

			if (isExist) {
				System.out.println("회원ID가" + memId + "인 회원은 이미 존재합니다");
				System.out.println("다시 입력해 주세요.");
			}
		} while (isExist);

		System.out.print("회원 이름 >> ");
		String memName = scan.next();

		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();

		scan.nextLine();

		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();

		////////////////////////////////////////////////////////////////////////

		try {
			// 옵션...
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc22_00001", "java");
			String sql = " insert into mymember(mem_id, mem_name,mem_tel,mem_addr) " + " values(?,?,?,?) ";

			SQL_LOGGER.debug("Query : " + sql);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);

			PARAM_LOGGER.debug("Parameter : memId = " + memId + ", memName = " + memName + ", memTel = " + memTel
					+ ", memAddr = " + memAddr);

			int cnt = pstmt.executeUpdate();

			RESULT_LOGGER.info("결과값 : {}", cnt);
			if (cnt > 0) {
				System.out.println(memId + "인 회원정보 등록 성공");
			} else {
				System.out.println(memId + "인 회원정보 등록 실패!!");
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 자원반납 close = finally 넣는이유 반드시 실행해야하기때문에
			JDBCUtil3.close(conn, stmt, pstmt, rs);

		}
	}

	// 회원정보 중복체크
	private boolean checkMember(String memId) {

		boolean isExist = false;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc22_00001", "java");
			String sql = " select count(*) as cnt\r\n" + "from mymember\r\n" + "where mem_id=? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("CNT");
			}

			if (cnt > 0) {
				isExist = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// 꼭! 마지막 자원반납해주기
			JDBCUtil3.close(conn, stmt, pstmt, rs);

		}

		return isExist;
	}

	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}
