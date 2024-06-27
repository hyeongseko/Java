package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/member/insert.do")
public class InsertMemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/member/insertForm.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");

		IMemberService memberservice = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();

		// 첨부파일 저장
		AtchFileVO atchFileVO = fileService.saveAtchFileList(req.getParts());

		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		int cnt = memberservice.registerMember(mv);

		String msg = "";

		if (cnt > 0) {
			msg = "SUCCESS";
		} else {
			msg = "FAIL";
		}

		req.getSession().setAttribute("msg", msg);

		// 포워딩 처리
		// req.getRequestDispatcher("/member/list.do").forward(req, resp);

		// redirect 처리
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
	}
}
