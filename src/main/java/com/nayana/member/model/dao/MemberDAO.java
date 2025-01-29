package com.nayana.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nayana.member.model.vo.Member;

public class MemberDAO {

	//1로그인!!
	public Member selectOneByLogin(Connection conn, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		//쿼리문 에러인지 아닌지 잘 확인할것!!
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		Member result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId()); //위치 홀더셋팅!!
			pstmt.setString(2, member.getMemberPwd());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				//rsetTomember~!!
				String memberId = rset.getString("MEMBER_ID");
				String memberName = rset.getString("MEMBER_NAME");
				String email = rset.getString("EMAIL");
				result = new Member(memberId, memberName, email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	//회원가입!Service에서 넘어옴!
	public int insertMember(Connection conn, Member member) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		//쿼리문에 위치홀더 생각하기!! 밑에는 9개니깐 ? 가 9개!!+DEFAULT!!
		//DEFAULT는 ENROLL_DATE 오늘날짜로 들어가야하니깐 넣어줌!!
		String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		//트라이 캐치 안해주고 어차피 Service에서 트라이캐치 예외처리를 해줬기때문에
		//여기서는 애드스로우로 던짐!!!그러면 코드가 조금줄어든다!!
		pstmt = conn.prepareStatement(query); 
		//번호는 상관없고 String인지 아닌지 확인필요!!
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPwd());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getGender());
		pstmt.setInt(5, member.getAge());//강제형변환이 필요한가??setString에서 SetInt로변경하면해결!!타입맞추기!int면 setInt!!
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getPhone());
		pstmt.setString(8, member.getAddress());
		pstmt.setString(9, member.getHobby());
		result = pstmt.executeUpdate();
		pstmt.close(); //트라이캐치가 없으니 훨씬 깔끔하다!!!
		return result;
	}

}
