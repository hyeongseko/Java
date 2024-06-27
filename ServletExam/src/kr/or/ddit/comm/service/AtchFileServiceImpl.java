package kr.or.ddit.comm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileDetailVO;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService {
	private static IAtchFileService fileService = new AtchFileServiceImpl();
	private IAtchFileDao fileDao;

	public AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}

	public static IAtchFileService getInstance() {
		return fileService;
	}

	@Override
	public AtchFileVO saveAtchFileList(Collection<Part> parts) {
		String uploadPath = "d:/D_Other.upload_files";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		AtchFileVO atchFileVO = null;

		boolean isFirstFile = true;

		for (Part part : parts) {
			// 업로드 파일명 추출
			String fileName = part.getSubmittedFileName();

			if (fileName != null && !fileName.equals("")) {
				if (isFirstFile) {
					isFirstFile = false;
					atchFileVO = new AtchFileVO();
					fileDao.insertAtchFile(atchFileVO);
				}

				long fileSize = part.getSize();
				String saveFileName = UUID.randomUUID().toString().replace("-", "");
				String saveFilePath = uploadPath + "/" + saveFileName;

				// 확장자 추출
				String fileExtension = fileName.lastIndexOf(".") < 0 ? ""
						: fileName.substring(fileName.lastIndexOf(".") + 1);

				// 업로드파일 저장
				try {
					part.write(saveFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				AtchFileDetailVO atchFileDetailVO = new AtchFileDetailVO();
				atchFileDetailVO.setAtchFileId(atchFileVO.getAtchFileId());
				atchFileDetailVO.setStreFileNm(saveFileName);
				atchFileDetailVO.setFileSize(fileSize);
				atchFileDetailVO.setOrignlFileNm(fileName);
				atchFileDetailVO.setFileStreCours(saveFilePath);
				atchFileDetailVO.setFileExtsn(fileExtension);

				// 파일세부 정보 저장
				fileDao.insertAtchFileDetail(atchFileDetailVO);

				try {
					// 임시 업로드 파일 삭제처리
					part.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return atchFileVO;
	}

	@Override
	public AtchFileVO getAtchFile(AtchFileVO fileVO) {

		return fileDao.getAtchFile(fileVO);
	}

	@Override
	public AtchFileDetailVO getatAtchFileDetail(AtchFileDetailVO atchFileDetailVO) {
		return fileDao.getAtchFileDetail(atchFileDetailVO);
	}

	public static void main(String[] args) {
		String fileName = "aaa.jpg";
		String fileExt = fileName.lastIndexOf(".") < 0 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);

		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}

}
