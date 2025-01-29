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
	public static JDBCTemplate getInstance() { //3.
		if (instance == null) {
			instance = new JDBCTemplate();
			
		}
		return instance;
	}
	public Connection getConnection() throws SQLException { //5.
		Connection conn = DriverManager.getConnection(URL,USERNAEM,PASSWORD);
		return conn;
	}
}	
