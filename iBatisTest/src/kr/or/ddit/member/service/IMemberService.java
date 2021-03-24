package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 회원정보 처리를 수행하는 서비스 
 */

public interface IMemberService {
	/**
	 * 회원등록하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 된다
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 회원ID
	 * @return 해당 회원ID가 존재하면 true, 존재하지 않으면 false
	 */
	public boolean checkMember(String memId);

	/**
	 * 전체 회원정보 조회 메서드
	 * DB의 mymember테이블의 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return 회원정보를 담고있는 List 객체
	 */
	public List<MemberVO> getAllMemberlist();
	
	
	/**
	 * 하나의 회원정보를 update하는 메서드
	 * @param mv 회원정보 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원 ID를 매개변수로 받아서 그 회원정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공 :1, 작업실패 : 0
	 */
	
	public int deleteMember(String memId);
	
	public List<MemberVO> getSearchMember(MemberVO mv);
	
}
