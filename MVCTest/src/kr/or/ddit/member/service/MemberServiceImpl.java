package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImplwithJDBC;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memDao;

	public MemberServiceImpl() {
		memDao = new MemberDaoImplwithJDBC();
	}

	@Override
	public int registerMember(MemberVO mv) {

		int cnt = memDao.insertMember(mv);
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {

		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		return memDao.checkMember(memId);
	}

	@Override
	public int removeMember(String memId) {

		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public List<MemberVO> getTotalMember() {

		List<MemberVO> memList = memDao.getAllMember();
		return memList;
	}

}
