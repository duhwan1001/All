package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법2) ResourceBundle객체 이용하기
 * @author PC-21
 *
 */
public class JDBCUtil3 {
	static ResourceBundle bundle; // ResourceBundle 객체변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("db"); //객체 생성
		
		try {
			Class.forName(bundle.getString("driver"));
		} catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패");
			ex.printStackTrace();
		}
	}
	
	/**
	 * 커넥션 객체 가져오기
	 * @return Connection 객체
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("user"), 
					bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			return null;
		}
	}

}
