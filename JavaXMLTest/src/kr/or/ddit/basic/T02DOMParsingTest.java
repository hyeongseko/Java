package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class T02DOMParsingTest {
	public void parse() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		// Document 객체 생성
		Document document = db.parse(new File("./src/kr/or/ddit/basic/new_book.xml"));

		// root Element 접근
		Element root = document.getDocumentElement();

		System.out.println("root Elememt : " + root.getTagName());

		// 하위 Element 접근
		NodeList bookNodeList = root.getElementsByTagName("book");

		// 첫번쨰 항목
		Node firstBookNode = bookNodeList.item(0);
		Element firstbookklElement = (Element) firstBookNode;

		// 속성값 접근
		System.out.println("첫번쨰 book의 isbn : " + firstbookklElement.getAttribute("isbn"));
		System.out.println("첫번째 book의 kind : " + firstbookklElement.getAttribute("kind"));

		// 전체 책 정보 출력
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("%8s %8s %12s &10s %8s\n", "ISBN", "분류", "제목", "기자", "가격");
		System.out.println("------------------------------------------------------------------------");
		;

		for (int i = 0; i < bookNodeList.getLength(); i++) {
			Element book = (Element) bookNodeList.item(i);

			String isbn = book.getAttribute("isbn");
			String kind = book.getAttribute("kind");
			String title = book.getElementsByTagName("title").item(0).getTextContent().trim();
			String author = book.getElementsByTagName("author").item(0).getTextContent().trim();
			String price = book.getElementsByTagName("price").item(0).getTextContent().trim();

			System.out.printf("%8s %8s %12s %10s %8s\n", isbn, kind, title, author, price);
		}
		System.out.println("------------------------------------------------------------------------");
	}

	public static void main(String[] args) throws Exception {
		new T02DOMParsingTest().parse();
	}
}
