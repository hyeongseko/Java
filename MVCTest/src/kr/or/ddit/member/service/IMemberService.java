package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {

   /**
    * membervo에 담겨진 회원정보를 등록하기 위한 메서드
    * @param mv 회원정보를 담은 membervo 객체
    * @return 작업이 성공하면 1, 실패하면 0 반환됨
    */
   public int registerMember(MemberVO mv);
   
   /**
    * membervo에 담겨진 회원정보를 수정하기 위한 메서드
    * @param mv 회원정보를 담은 membervo 객체
    * @return 작업이 성공하면 1, 실패하면 0 반환됨
    */
   public int modifyMember(MemberVO mv);
   
   /**
     * 해당회원의 존재여부를 확인하기 위한 메서드
     * @param memid 존재여부 확인하기 위한 회원id
     * @return 해당회원이 존재하면 true, 존재하지 않으면 false리턴함
     */
   
   public boolean checkMember(String memId);
   
   /**
    * 해당회원정보를 삭제하기 위한메서드
    * @param memid 삭제하고자 하는 회원id
    * @return 삭제처리가 성공하면 1, 실패하면 0반환됨
    */
   
   public int removeMember(String memId);
   
   /**
    * 모든 회원정보를 가져오는 위한 메서드
    * @return 모든 회원 정보를 담은 list 객체
    * 
    */
    public List<MemberVO> getTotalMember();
}
