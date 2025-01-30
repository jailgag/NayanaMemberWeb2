package com.nayana.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.nayana.common.JDBCTemplate;
import com.nayana.member.model.dao.MemberDAO;
import com.nayana.member.model.vo.Member;

public class MemberService {
	
	private JDBCTemplate jdbcTemplate;//1.
	
	private MemberDAO mDao;  //3.
	
	public MemberService() { //2.
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO(); //4 객체만들기!
	}
	//1.로그인!!
	public Member selectOneByLogin(Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Member result = null;  //그래서 옆에 코드 Member result = null선언!!!
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.selectOneByLogin(conn,member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result; //여기서 result는 위에 타입이 public Member selectOneByLogin
				//	Member니깐 member값result!!
		// 			그래서 선언할때 윗코드Member result = null; 가됨!!
		//아래 public int insertMember(Member member) 도!!!
		// int니깐 int result = 0;!!?????
		//그값을 꼭 맞춰줘야한다!!! 
	}
	//signupServlete에서 넘어옴!!회원가입!!
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		//DML!
		int result = 0;
		Connection conn = null;
		//conn = jdbcTemplate.getConnection();연결을 만들어주고!서라운드 트라이캐치!!
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(conn, member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result; //result값이위에public int insertMember ~타입이 int니깐 
					//int result!! 
	}
	//mypageServlet에서 옴!마이페이지!!
	public Member selectOneByid(String memberId) {
		// TODO Auto-generated method stub
		Member member = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection(); //트라이캐치!
			member = mDao.selectOneById(conn,memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member; //
	}
	//updateServlet에서 넘어옴!
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
	//insertMember랑 거의똑같다!!
		int result = 0;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(conn, member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	//DeleteServlete에서 넘어옴!!탈퇴!!
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		//위에 updateMember랑 비슷하다!!
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.deleteMember(conn, memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	

}
