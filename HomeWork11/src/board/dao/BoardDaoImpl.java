package board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import board.vo.BoardVO;
import homework09.util.MyBatisUtil;

public class BoardDaoImpl implements IBoardDao {
	private static IBoardDao boardDao = new BoardDaoImpl();

	public BoardDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public static IBoardDao getInstance() {
		return boardDao;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardlist = new ArrayList<BoardVO>();

		SqlSession session = MyBatisUtil.getSqlSession(true);
		try {
			boardlist = session.selectList("board.selectAllBoard");
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardlist;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		SqlSession session = MyBatisUtil.getSqlSession();
		int cnt = 0;
		try {
			cnt = session.insert("board.insertBoard", bv);
			if (cnt > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		SqlSession session = MyBatisUtil.getSqlSession();
		int cnt = 0;
		try {
			cnt = session.insert("board.updateBoard", bv);
			if (cnt > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		boolean isExist = false;
		SqlSession session = MyBatisUtil.getSqlSession(true);
		int cnt = 0;
		try {
			cnt = session.selectOne("board.checkBoard", boardNo);
			if (cnt > 0) {
				isExist = true;
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return isExist;
	}

	@Override
	public int deleteBoard(String boardNo) {
		SqlSession session = MyBatisUtil.getSqlSession();
		int cnt = 0;
		try {
			cnt = session.delete("board.deleteBoard", boardNo);

			if (cnt > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardlist = new ArrayList<BoardVO>();

		SqlSession session = MyBatisUtil.getSqlSession(true);
		try {
			boardlist = session.selectList("board.searchBoard", bv);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardlist;
	}
}
