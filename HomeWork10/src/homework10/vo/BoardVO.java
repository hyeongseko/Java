package homework10.vo;

import java.time.LocalDate;

public class BoardVO {
	private String boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;

	private LocalDate boarDate;

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public LocalDate getBoarDate() {
		return boarDate;
	}

	public void setRegDt(LocalDate boarDate) {
		this.boarDate = boarDate;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", regDt=" + boarDate + "]";
	}

}
