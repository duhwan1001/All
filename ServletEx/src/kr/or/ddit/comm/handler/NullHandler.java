package kr.or.ddit.comm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 해당 요청을 처리할 핸들러를 발견하지 못한 경우 호출됨
 */
public class NullHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 not found 에러
		
		return null;
	}
	
}
