package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T05ServletSessionTest extends HttpServlet {
	/*
	 * HttpSession 객체...
	 * 
	 * ** 세션 객체를 이용하여 사용자 별로 구분하여 정보를 관리 가능 (세션ID 이용)
	 * 
	 * ** 쿠키를 사용 할 때보다 보안이 향상 (정보가 서버에 저장되기 떄문에)
	 */

	/*
	 * - 세션 객체를 가져오는 방법
	 * 
	 * HttpSession session = req.getSession(boolean값);
	 * 
	 * boolean값 : true인 경우 => 세션객체가 존재하지 않으면 새로 생성하여 반환 false인 경우 => 세션객체가 존재하지 않으면
	 * null 리턴
	 */

	/*
	 * - 세션 객체를 삭제처리
	 * 
	 * 1. invaludate()메소드 이용
	 * 
	 * 2. setMaxInactiveInterval(int interval) 메서드 이용 => (초단위)
	 * 
	 * 3. web.xml 파일에 <session-config> 설정 => (분단위) *
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 세션객체를 가져오는데 없으면 새로 생성한다.
		HttpSession httpSession = req.getSession(true);

		// 세션 생성시간 가져오기
		Date createTime = new Date(httpSession.getCreationTime());

		// 세션에 마지막 접근한 시간 가져오기
		Date lastAccessTime = new Date(httpSession.getLastAccessedTime());

		String title = "재방문을 환영합니다.";
		int visitCnt = 0; // 방문횟수
		String userId = "mun"; // 사용자 ID

		if (httpSession.isNew()) { // 새로 만들어진 세션객체인지 확인
			title = "처음 방문을 환영합니다.";
			httpSession.setAttribute("userId", userId);
		} else {
			visitCnt = (Integer) httpSession.getAttribute("visitCnt");
			visitCnt++;
			userId = (String) httpSession.getAttribute("userId");
		}
		httpSession.setAttribute("visitCnt", visitCnt);

		// 세션 무효화 처리
		// httpSession.invalidate();

		httpSession.setMaxInactiveInterval(30);

		// --------------------------------------------------------------------------

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.print("<!DOC_TYPE html><html><head><title>" + title + "</title></head>" + "<body><h1 align=\"center\">"
				+ title + "</h1>" + "<h2 align=\"center\">세션 정보</h2>" + "<table border=\"1\" align=\"center\">"
				+ "<tr bgcolor=\"orange\"><th>구분</th><th>값</th></tr>" + "<tr><td>세션ID</td><td>" + httpSession.getId()
				+ "</td></tr>" + "<tr><td>생성시간</td><td>" + createTime + "</td></tr>" + "<tr><td>마지막 접근시간</td><td>"
				+ lastAccessTime + "</td></tr>" + "<tr><td>사용자ID</td><td>" + userId + "</td></tr>"
				+ "<tr><td>방문횟수</td><td>" + visitCnt + "</td></tr>" + "</table></body><html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
