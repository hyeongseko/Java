package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// db.properties파일의 내용으로 DB정보를 설정
public class JDBCUtil2 {

	static Properties prop;

	static {

		prop = new Properties();

		try {
			prop.load(new FileInputStream("./res/db.properties"));
			Class.forName(prop.getProperty("driver"));

			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패!!!");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 자원반납
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
				// TODO: handle exception
			}
	}
}
