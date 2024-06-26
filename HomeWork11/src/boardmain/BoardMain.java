package boardmain;

import java.util.List;
import java.util.Scanner;

import board.service.BoardServiceImpl;
import board.service.IBoardService;
import board.vo.BoardVO;

public class BoardMain {
	private IBoardService boardService;

	private Scanner scan;

	public BoardMain() {
		boardService = BoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
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
		System.out.println("---------------------------------------------------");
		System.out.println("번 호\t글 제목\t\t작성자\t작성 날짜\t\t내용");
		System.out.println("---------------------------------------------------");

		List<BoardVO> boardList = boardService.getTotalBoard();

		if (boardList.size() == 0) {
			System.out.println("회원정보가 없습니다.");
		} else {
			for (BoardVO bd : boardList) {
				System.out.println(bd.getBoardNo() + "\t" + bd.getBoardTitle() + "\t\t" + bd.getBoardWriter() + "\t"
						+ bd.getBoardDate() + "\t" + bd.getBoardContent());
			}
		}
		System.out.println("---------------------------------------------------");
	}

	private void searchBoard() {
		scan.nextLine();

		System.out.print("찾는 게시판 번호를 적으세요 >> ");
		String boardNo = scan.nextLine().trim();

		System.out.print("찾는 글 제목을 적으세요 >> ");
		String boardTitle = scan.nextLine().trim();

		System.out.print("찾는 작성자를 적으세요 >> ");
		String boardWriter = scan.nextLine().trim();

		System.out.print("찾는 내용의 글자를 적으세요 >> ");
		String boardContent = scan.nextLine().trim();

		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);

		List<BoardVO> boardList = boardService.searchBoard(bv);

		if (boardList.size() == 0) {
			System.out.println("---------------------------------------------------");
			System.out.println("게시판 정보가 없습니다.");
			System.out.println("---------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------");
			System.out.println("번호\t글 제목\t\t작성자\t작성 날짜\t\t내용");
			System.out.println("---------------------------------------------------");
			for (BoardVO bv2 : boardList) {
				System.out.println(bv2.getBoardNo() + "\t" + bv2.getBoardTitle() + "\t\t" + bv2.getBoardWriter() + "\t"
						+ bv2.getBoardDate() + "\t" + bv2.getBoardContent());
			}
		}
	}

	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호를 입력해 주세요.");
		System.out.print("게시판 번호 >> ");
		String boardNo = scan.next();

		int cnt = boardService.removeBoard(boardNo);

		if (cnt > 0) {
			System.out.println("delete 성공");
		} else {
			System.out.println("delete 실패");
		}
	}

	private void updateMember() {

		boolean isExist = false;
		String boardNo = "";
		do {
			System.out.print("수정할 게시판 번호 >> ");
			boardNo = scan.next();
			isExist = boardService.checkBoard(boardNo);
			if (!isExist) {
				System.out.println(boardNo + "의 게시판이 없습니다.");
			}
		} while (!isExist);

		scan.nextLine();
		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.nextLine().trim();

		System.out.print("작성자 이름 >> ");
		String boardWriter = scan.nextLine().trim();

		System.out.print("게시판 내용 >> ");
		String boardContent = scan.nextLine().trim();

		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		bv.setBoardNo(boardNo);

		////////////////////////////////////////////////////////////////////////

		int cnt = boardService.modifyBoard(bv);
		if (cnt > 0) {
			System.out.println("update 작업 성공!!!");
		} else {
			System.out.println("update 작업 실패!!!");
		}
	}

	private void insertBoard() {
		System.out.print("게시판 이름 >> ");
		String boardTitle = scan.next();

		scan.nextLine();

		System.out.print("작성자 >> ");
		String boardWriter = scan.next();

		scan.nextLine();

		System.out.print("내용 >> ");
		String boardContent = scan.nextLine();
		///////////////////////////////
		BoardVO bd = new BoardVO();
		bd.setBoardTitle(boardTitle);
		bd.setBoardWriter(boardWriter);
		bd.setBoardContent(boardContent);

		int cnt = boardService.registerBoard(bd);
		if (cnt > 0) {
			System.out.println("insert 작업 성공!!!");
		} else {
			System.out.println("insert 작업 실패!!!");
		}
	}

}
