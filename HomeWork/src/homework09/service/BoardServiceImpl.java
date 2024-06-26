package homework09.service;

import java.util.List;

import homework09.dao.BoardDaoImplwitJDBC;
import homework09.dao.IBoardDao;
import homework09.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao boardDao;
	private static BoardServiceImpl boardService = new BoardServiceImpl();

	private BoardServiceImpl() {
		boardDao = BoardDaoImplwitJDBC.getInstance();
	}

	public static BoardServiceImpl getInstance() {
		return boardService;
	}

	@Override
	public int registerBoard(BoardVO bv) {
		int cnt = boardDao.insertBoard(bv);
		return cnt;
	}

	@Override
	public int modifyBoard(BoardVO bv) {
		int cnt = boardDao.updateBoard(bv);
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		return boardDao.checkBoard(boardNo);
	}

	@Override
	public int removeBoard(String boardWriter) {
		int cnt = boardDao.deleteBoard(boardWriter);
		return cnt;
	}

	@Override
	public List<BoardVO> getTotalBoard() {
		List<BoardVO> boardList = boardDao.getAllBoard();
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(String boardWriter) {
		List<BoardVO> boardList = boardDao.searchBoard(boardWriter);
		return boardList;
	}

}
