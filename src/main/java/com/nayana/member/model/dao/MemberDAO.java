package com.nayana.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nayana.member.model.vo.Member;

public class MemberDAO {

	//1로그인!!
	public Member selectOneByLogin(Connection conn, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null; 
		//SELECT~니깐 ResultSet이 필요함!!아래코드!
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
		//Service에서 SQL캐치를 해줬기때문dp DAO 여기선 트라이캐치가 없고 애드스로우로 던짐!!
		//위에는 ??
		pstmt = conn.prepareStatement(query); 
		//번호는 상관없고 String인지 아닌지 확인필요!!그리고 
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPwd());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getGender());
		pstmt.setInt(5, member.getAge());//강제형변환이 필요한가??setString에서 SetInt로변경하면해결!!타입맞추기!int면 setInt!!
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getPhone());
		pstmt.setString(8, member.getAddress());
		pstmt.setString(9, member.getHobby());
		//밑에코드는 sql Developer 에 콘트롤 엔터를 누르는 거랑 똑같은거다!!!
		result = pstmt.executeUpdate(); //옆에코드는 쿼리문 실행하는 코드!!DML?이니깐?!!
		pstmt.close(); //트라이캐치가 없으니 훨씬 깔끔하다!!!
		return result;
	}
	//Service에서 넘어옴!마이페이지!!
	public Member selectOneById(Connection conn, String memberId) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null; //에러나면 임포트확인!
		String query ="SELECT * FROM MEMBER_TBL WHERE MEMBER_ID= ?";
		//아래코드도 insertmember 할때도 마찬가지로 애드 스로우로던짐!!
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberId);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			//rsetToMember 만들기!!
			member = rsetToMember(rset);
		}
		rset.close();
		pstmt.close();
		return member;
	}
	//memberService에서 넘어왔고 online으로 올려줌!
	//Update!!!
	public int updateMember(Connection conn, Member member) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		//java.sql.SQLSyntaxErrorException: ORA-00971: SET 키워드가 없습니다 *SET_언더바오입력!
		//String query ="UPDATE MEMBER_TBL_SET EMAIL =? , PHONE=? , ADDRESS=?, HOBBY=? WHERE MEMBER_ID= ?";
		String query ="UPDATE MEMBER_TBL SET EMAIL =? , PHONE=? , ADDRESS=?, HOBBY=? WHERE MEMBER_ID= ? ";
		pstmt = conn.prepareStatement(query); 
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPhone());
		pstmt.setString(3, member.getAddress());
		pstmt.setString(4, member.getHobby());
		pstmt.setString(5, member.getMemberId());
		result = pstmt.executeUpdate();//DML이니깐!!(이거 헷갈려서 sql복습할때 정리할것!)
		pstmt.close();
		return result;
	}
	//MemberService에서 넘어옴 위로올려줌!!
	public int deleteMember(Connection conn, String memberId) throws SQLException {
		// TODO Auto-generated method stu
		//위에 updateMember랑비슷함!!
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="DELETE FROM MEMBER_TBL WHERE MEMBER_ID= ? ";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberId);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	//rsetTomember만들기!!
	private Member rsetToMember(ResultSet rset) throws SQLException {
		// TODO Auto-generated method stub
		//Member member = new Member(m~~
		//가 있어서 밑에코드는 없어도됨!!
		//Member member = null;
		String memberId = rset.getString("MEMBER_ID"); //애드 스로우!!
		String memberPwd = rset.getString("MEMBER_PWD");
		String memberName =rset.getString("MEMBER_NAME");
		String gender =  rset.getString("GENDER");
		int age = rset.getInt("AGE");
		String email = rset.getString("EMAIL");
		String phone = rset.getString("PHONE");
		String address = rset.getString("ADDRESS");
		String hobby = rset.getString("HOBBY");
		Date enrollDate = rset.getDate("ENROLL_DATE");
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate); //생성자만들기!
		return member;
	}

}
