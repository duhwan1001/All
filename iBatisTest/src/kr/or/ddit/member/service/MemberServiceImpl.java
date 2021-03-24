package kr.or.ddit.member.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientUtil;

public class MemberServiceImpl implements IMemberService {

	// 사용할 DAO의 객체변수를 선언한다.
	private IMemberDao memDao; // ★다형성 관련 알아보기
	private SqlMapClient smc;
	
	private static IMemberService memService;
	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
		smc = SqlMapClientUtil.getInstance();
	}
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt =0;
		try {
			// default는 바로 commit
//			smc.startTransaction(); // 수동 트랜잭션 시작
			cnt = memDao.insertMember(smc, mv); // 이전에 예외처리를 하지않고 throw던졌기에 error나는 것
//			smc.commitTransaction(); // 수동 트랜잭션 커밋
//			smc.endTransaction();  // 수동 트랜잭션 종료
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk =false;
		try {
			chk = memDao.checkMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return chk;
	}

	
	@Override
	public List<MemberVO> getAllMemberlist() {
		List<MemberVO> memList = new ArrayList<>();
		try {
			memList = memDao.getAllMemberlist(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		
		int cnt=0;
		try {
			cnt = memDao.updateMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt=0;
		try {
			cnt = memDao.deleteMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = memDao.searchMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	} 
		
}
