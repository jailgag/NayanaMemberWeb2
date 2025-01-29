package com.nayana.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAEM = "KH";
	private static final String PASSWORD = "KH";
	private static JDBCTemplate instance; ///1.
	
	private JDBCTemplate() { //2.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//4.드라이버등록
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//JDTBTemplate객체가 싱글톤패턴적용해서 사용!
	public static JDBCTemplate getInstance() { //3.
		if (instance == null) {
			instance = new JDBCTemplate();
			
		}
		return instance;
	}
	//getConnection이라는 메소드를 통해서 연결을 만들어줌!!
	public Connection getConnection() throws SQLException { //5.
		Connection conn = DriverManager.getConnection(URL,USERNAEM,PASSWORD);
		return conn;
	}
}	
