package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02_ServletTest extends HttpServlet{
	
	/**
	 * 서블릿 동작 방식에 대하여
	 * 1. 사용자가 URL을 클릭하면 HTTP Request를
	 *  서블릿 컨테이너로 전송(요청)한다.
	 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해
	 * 처리할지를 검색한다.
	 * 3. Servelet Container는 요청을 처리할 개별 스레드 객체를 생성하여
	 * 해당 서블릿 객체의 Serivce() 메서드를 호출한다.
	 * (httpServletRequest 및 HttpServletResponse
	 * 객체를 세생성하여 파라미터로 넘겨준다.
	 * serivce() 메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다.
	 * (doGet, doPost, doPut, doDelete)
	 * 5. 요청 처리가 완료되면, (HttpServerRequest 및 HttpServiceletResponse 객체는 소멸한다.)
	 * 6. 컨테이너로부터 서블릿이 제거되는 경우에 destroy() 메서드가 호출된다. 
	 */
	@Override
		//웹에서 폰트가 깨지지 않기 위해 server.xml 65번째 열에 URIEncoding="UTF-8" 추가함
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post방식으로 넘어오는 Body데이터를 인코딩처리함. get방식은 톰캣의 URIEncoding 설정을 이용함
		// 반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용됨.
		// req.setCharacterEncoding("utf-8");
		
		// 요청정보로부터 name값을 가져옴.
		String name = req.getParameter("name");
		
		System.out.println("name => " + name);
		
		// 응답메시지 인코딩 설정(Content-Type의 charset=UTF-8)
		// kr.or.ddit.filter.CustomEncoding 에서 UTF-8 설정해줬음. 그래서 주석처리함
		//resp.setCharacterEncoding("UTF-8");
		
		// 응답 메시지의 컨텐트 타입 설정
		resp.setContentType("text/plain"); // MIME
		
		// 실제 수행할 로직(기능)이 시작되는 부분
		PrintWriter out = resp.getWriter();
		out.println("name =>" + name);
		out.println("서블릿 경로 : " + req.getServletPath());
		out.println("컨텍스트 경로 : " + req.getContextPath());
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}




















