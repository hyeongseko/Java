package board.service;

import java.util.List;

import board.vo.BoardVO;

public interface IBoardService {

	public int registerBoard(BoardVO bv);

	public int modifyBoard(BoardVO bv);

	public boolean checkBoard(String boardNo);

	public int removeBoard(String boardNo);

	public List<BoardVO> searchBoard(BoardVO bv);

	public List<BoardVO> getTotalBoard();
}
