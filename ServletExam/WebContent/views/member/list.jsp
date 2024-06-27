<%--
	1. Directive
		JSP페이지에 대한 설정정볼르 지정할 때 사용
			(page, taglib, include)
		'<%@'로 시작하고 그 뒤에 디렉티브 이름이 오고 
			필요에 따라 속성명이 올수 있으며, 마지막에 '%>'로 끝낸다.
		ex) <%@ 디레티브이름 속성명 \ "속성값" ... %>
		
	2. Scripte 요소
		JSP에서 문서의 내용을 동적으로 생성하기 위해 사용
		- 표현식 (Expression) : 값을 출력결과에 포함시키고자 할떄 사용
			ex) <%=값%>
		- 스크립트릿(Scriptlet) : 자바코드를 작성할 떄 사용
			ex) <% 자바코드를...%>
		- 선언부(Declaration) : Scriptlet이나 표현식에서 사용 할 수 있는 메서드를 작성할 떄 사용
			ex) <%! ~~~ %>
	
	 3. JSP 기본객체와 영역(SCOPE)
 
 		- PAGE 영역 : 하나의 JSP페이지를 처리할 때 사용되는 영역 
 			=> pageContext
 		- REQUEST 영역 : 하나의 HTTP요청을 처리할 때 사용되는 영역 
 			=> request
 		- SESSION 영역 : 하나의 웹브라우저(사용자)와 관련된 영역 
 			=> session
 		- APPLICATION 영역 : 하나의 웹애플리케이션과 관련된 영역 => application
 --%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");
String msg = session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");

session.removeAttribute("msg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>

		<%
			int memSize = memList.size();
		if (memSize > 0) {
			for (MemberVO mv : memList) {
		%>

		<tr>
			<td><%=mv.getMemId()%></td>
			<td><a
				href="<%=request.getContextPath()%>/member/detail.do?memId=<%=mv.getMemId()%>"><%=mv.getMemName()%></a></td>
			<!--이름에 하이퍼링크  -->
			<td><%=mv.getMemTel()%></td>
			<td><%=mv.getMemAddr()%></td>
		</tr>

		<%
			} // for문
		} else {
		%>

		<tr>
			<td colspan="4">회원정보가 존재하지 않습니다.</td>
		</tr>

		<%
			} // if문
		%>
		<tr align="center">
			<td colspan="4"><a
				href="<%=request.getContextPath()%>/member/insert.do">[회원 등록]</a></td>
		</tr>

	</table>
	<%
		if (msg.equals("SUCCESS")) {
	%>
	<script>
		alert('정상적으로 처리완료');
	</script>
	<%
		}
	%>
</body>
</html>