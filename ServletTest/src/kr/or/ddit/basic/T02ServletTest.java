package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02ServletTest extends HttpServlet {
	/*
	 * Servlet 동작 방식에 대하여...
	 * 
	 * 1. 사용자(Client)가 URL을 클릭하면 HTTP 요청메세지를 Servlet 컨테이너로 전송
	 * 
	 * 2. Servlet 컨테이너는 web.xml에 설정된 url패턴정보를 확인하여 어느 Servlet을 통해 처리할 것인지 검색
	 * (Servlet이 컨테이너에 로딩이 안된경우에는 먼저 생성하여 처리함. init() 호출)
	 * 
	 * 3. Servlet 컨테이너는 요청을 처리할 개별 Thread를 생성하여 해당 Servlet 객체의 service() 호출
	 * (HttpServletRequst 및 HttpServletResponce 객체를 생성하여 파라미터로 전송)
	 * 
	 * 4. Service()는 메서드 타입을 확인하여 적절한 method 호출 (doGet, doPost, doPut, doDelete 등)
	 * 
	 * 5. 요청 처리가 완료되면, HttpServletReauest 및 HttpServletResponse 객체는 소멸
	 * 
	 * 6. 컨테이너로부터 Servlet이 제거되는 경우에 destroy()가 호축
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("getCharcharterEncoding() : " + req.getCharacterEncoding());
		System.out.println("getContentLenght() : " + req.getContentLength()); // get은 body정보가 없어서 '-1' 출력, post는 '1'출력
		System.out.println("getQueryString() : " + req.getQueryString());
		System.out.println("getProtocol() : " + req.getProtocol());
		System.out.println("getMethod() : " + req.getMethod());
		System.out.println("getRequestURI() : " + req.getRequestURI());
		System.out.println("getHeaderNames() : " + req.getHeaderNames());
		System.out.println("getRemoteAddr() : " + req.getRemoteAddr()); // 주소창에서 localhost대신 상대 ip주소 입력
		System.out.println("getRemotePort() : " + req.getRemotePort());
		//////////////////////////////////////////////////////////////////////////////////

		// 요청메세지로부터 name값 가져오기
		String name = req.getParameter("name");

		System.out.println("name : " + name);

		// 요청객체에 데이터 저장하기
		req.setAttribute("tel", "1234-567");
		req.setAttribute("addr", "대전시 중구 오류동");

		// 요청객체에 저장된 데이터 꺼내오기
		System.out.println("tel => " + req.getAttribute("tel"));
		System.out.println("addr => " + req.getAttribute("addr"));

		/////////////////////////////////////////////////////////////
		// 응답 메세지 생성

		// 응답메세지의 컨테느 타입정보 설정
		// resp.setContentType("text/plain; charset= UTF-8");
		// resp.setContent("");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain;");

		// 응답내용 작성
		PrintWriter out = resp.getWriter();

		out.println("name => " + name);
		out.println("서블릿 경로 : " + req.getServletPath());
		out.println("서블릿 컨텍스트 경로 : " + req.getContextPath());

		// 응답 메세지 생성 끝
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
