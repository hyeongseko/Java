package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3부터 지원하는 Part 인터페이스를 이용한 파일 업로드
 */
// 메모리 임계 크기 (이 크키가 넘으면 레파지토리 위치에 임시파일로 저장
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3,
		// 파일 1개당 최대 크기
		maxFileSize = 1024 * 1024 * 4,
		// 요청파일 최대 크기
		maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/uploadTest")
public class T10ServletUploadTest extends HttpServlet {
	private static final String UPLOAD_DIR = "upload_files";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("===================================================================================");
//		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//		String readLine = "";
//		while((readLine=br.readLine())!= null) {
//			System.out.println(readLine);	
//		}
//		
//		System.out.println("==================================================================================");

		// Multipart 파싱전 파라미터 정보 불러오기
		System.out.println("Multipart 파싱 전 sender : " + req.getParameter("sender"));

		String uploadPath = req.getServletContext().getRealPath("/") + File.separator + UPLOAD_DIR;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			String fileName = "";

			for (Part part : req.getParts()) {
				System.out.println(part.getHeader("Content-Disposition"));
				// fileName = getFileName(part);

				fileName = part.getSubmittedFileName();

				if (fileName != null && !fileName.equals("")) {
					part.write(uploadPath + File.separator + fileName);

					System.out.println("업로드 처리 완료...");
					System.out.println(uploadPath + File.separator + fileName);

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Part객체 파싱하여 파일이름 추출
	 * 
	 * @param part 파일이름 파싱할 Part 객체
	 * @return 파일명 : 파일명이 존재하지 않으면 NULL값 리턴
	 */
	private String getFileName(Part part) {

		for (String content : part.getHeader("Content-Disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}

		return null;
	}
}
