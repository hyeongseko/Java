package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/* 
 	실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해
	서비스에 전달하기 위한 DAO Interface
*/

public interface IMemberDao {

	/**
	 * MemberVO에 담겨진 회원정보를 DB에 INSERT my 회원정보를 담은 MemberVO 객체 DB작업이 성공하면 1, 실패하면 0
	 * 반환
	 * 
	 * @param mv
	 * @return
	 */
	public int insertMember(MemberVO mv);

	// MemberVO에 담겨진 회원정보를 DB에 Update
	// my 회원정보를 담은 MemberVO 객체
	// DB작업이 성공하면 1, 실패하면 0 반환
	public int updateMember(MemberVO mv);

	// 해당 회원의 존재여부를 확인하기 위한 메서드
	// memID 존재여부 확인하기 위한 회원ID
	// 회원이 존재하면 true, 존재하지 않으면 false
	public boolean checkMember(String memId);

	// 해당 회원정보를 삭제하기 위한 메서드
	// memId 삭제하고자 하는 회원 ID
	// 삭제처리 성공하면 1, 실패하면 0 반환
	public int deleteMember(String memId);

	// DB에 존재하는 모든 회원정보를 가져오기
	// 모든회원정보를 담은 List 객체
	public List<MemberVO> getAllMember();

}
