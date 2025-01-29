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
		Member result = null;
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
		
		return result;
	}
	//signupServlete에서 넘어옴!!회원가입!!
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
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
		
		return result;
	}
	

}
