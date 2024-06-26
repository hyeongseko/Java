package homework09.service;

import java.util.List;

import homework09.vo.BoardVO;

public interface IBoardService {

	public int registerBoard(BoardVO bv);

	public int modifyBoard(BoardVO bv);

	public boolean checkBoard(String boardNo);

	public int removeBoard(String boardNo);

	public List<BoardVO> searchBoard(String boardWriter);

	public List<BoardVO> getTotalBoard();
}
