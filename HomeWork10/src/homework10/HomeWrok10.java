package homework10;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import homework10.vo.BoardVO;

public class HomeWrok10 {
	private SqlSessionFactory sqlSessionFactory;
	private Scanner scan = new Scanner(System.in);

	public HomeWrok10() {
		try {
			// 1-1 XML설정 파일 읽어오기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);

			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");

			// 1-2. 위에서 읽어온 Reader 객체를 이용하여 SqlSessionFactory 객체를 생성한다.
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);

			// 스트림 닫기
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HomeWrok10 board = new HomeWrok10();
		board.start();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("---------------------------");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새글 작성");
		System.out.println("  3. 수정");
		System.out.println("  4. 삭제");
		System.out.println("  5. 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("---------------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1:
				displayAllBoard();
				break;
			case 2:
				insertBoard();
				break;
			case 3:
				updateMember();
				break;
			case 4:
				deleteBoard();
				break;
			case 5:
				searchBoard();
				break;
			case 6:
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void displayAllBoard() {
		SqlSession session = sqlSessionFactory.openSession(true);
		try {
			List<BoardVO> boardList = session.selectList("memberTest.selectAllBoard");
			System.out.println("---------------------------------------------------");
			System.out.println("번호\t글 제목\t작성자\t작성 날짜\t\t내용");
			System.out.println("---------------------------------------------------");
			for (BoardVO bd : boardList) {
				System.out.println(bd.getBoardNo() + "\t" + bd.getBoardTitle() + "\t" + bd.getBoardWriter() + "\t"
						+ bd.getBoarDate() + "\t" + bd.getBoardContent());
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("------------------------------------------------------------");

	}

	private void searchBoard() {
		System.out.print("찾는 작성자를 적으세요 >> ");
		String boardWriter = scan.next();
		SqlSession session = sqlSessionFactory.openSession(true);
		try {
			List<BoardVO> boardList = session.selectList("memberTest.selectBoard", boardWriter);

			System.out.println("---------------------------------------------------");
			System.out.println("번호\t글 제목\t작성자\t작성 날짜\t\t내용");
			System.out.println("---------------------------------------------------");
			for (BoardVO bd : boardList) {
				System.out.println(bd.getBoardNo() + "\t" + bd.getBoardTitle() + "\t" + bd.getBoardWriter() + "\t"
						+ bd.getBoarDate() + "\t" + bd.getBoardContent());
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호를 입력해 주세요.");
		System.out.print("게시판 번호 >> ");
		String boardNo = scan.next();

		SqlSession session = sqlSessionFactory.openSession();
		try {
			int cnt = session.delete("memberTest.deleteBoard", boardNo);

			if (cnt > 0) {
				System.out.println("delete 성공");
				session.commit();
			} else {
				System.out.println("delete 실패");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void updateMember() {
		System.out.print("게시판 번호 >> ");
		String boardNo = scan.next();

		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.next();
		scan.nextLine();

		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.next();

		scan.nextLine();

		System.out.print("게시판 내용 >> ");
		String boardContent = scan.nextLine();

		BoardVO bd = new BoardVO();
		bd.setBoardNo(boardNo);
		bd.setBoardTitle(boardTitle);
		bd.setBoardWriter(boardWriter);
		bd.setBoardContent(boardContent);

		////////////////////////////////////////////////////////////////////////
		SqlSession session = sqlSessionFactory.openSession(false);

		try {
			int cnt = session.insert("memberTest.updateBoard", bd);

			if (cnt > 0) {
				System.out.println("update 작업 성공!!!");
				session.commit();
			} else {
				System.out.println("update 작업 실패!!!");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void insertBoard() {
		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.next();
		scan.nextLine();

		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.next();

		scan.nextLine();

		System.out.print("게시판 내용 >> ");
		String boardContent = scan.nextLine();

		///////////////////////////////
		BoardVO bd = new BoardVO();
		bd.setBoardTitle(boardTitle);
		bd.setBoardWriter(boardWriter);
		bd.setBoardContent(boardContent);

		SqlSession session = sqlSessionFactory.openSession(false);

		try {
			int cnt = session.insert("memberTest.insertBoard", bd);

			if (cnt > 0) {
				System.out.println("insert 작업 성공!!!");
				session.commit();
			} else {
				System.out.println("insert 작업 실패!!!");
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

}
