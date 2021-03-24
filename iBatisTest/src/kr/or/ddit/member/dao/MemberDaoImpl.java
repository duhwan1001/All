package kr.or.ddit.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientUtil;

public class MemberDaoImpl implements IMemberDao{
	
	private static IMemberDao memDao;
	
	private MemberDaoImpl() {
	
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		int cnt=0;
		Object obj = smc.insert("member.insertMember",mv);
		System.out.println(obj);
		if(obj == null) { // insert는 성공시 null을 반환(object형)
			cnt = 1;
		}
		
		return cnt;
	}

	@Override // 단일행 출력(obeject형 출력)
	public boolean checkMember(SqlMapClient smc, String memId) throws SQLException {
		boolean chk = false;
		int cnt = (int) smc.queryForObject("member.getMember",memId);
		if(cnt > 0 ) {
			chk = true;
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberlist(SqlMapClient smc) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getMemberAll");
		
		return memList;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) throws SQLException {

		int cnt = smc.update("member.updateMember", mv);
			
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		int cnt = smc.delete("member.deleteMember",memId);
		
		return cnt;
		
	}

	@Override // 다중행 출력(List반환)
	public List<MemberVO> searchMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = smc.queryForList("member.getSearchMember",mv);
		
		return memList;
	}
	
}
