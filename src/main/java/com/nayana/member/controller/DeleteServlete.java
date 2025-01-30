package com.nayana.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.nayana.member.model.service.MemberService;
import com.nayana.member.model.vo.Member;

//import com.nayana.member.model.vo.Member;

/**
 * Servlet implementation class DeleteServlete
 * ****a태그로 요청하는것은 Doget만 필요함!!
 * 
 */
@WebServlet("/member/delete")
public class DeleteServlete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿼리문 DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		//delete는 세션에서 가져와야한다!로그인한상태에서탈퇴하기때문!
		HttpSession session = request.getSession();//이코드 자주쓰인다!!
		//아래코드는 다운캐스팅코드!!
		Member member =(Member)session.getAttribute("member");
		if(member != null) {
			MemberService mService = new MemberService();
			String memberId = member.getMemberId();
			int result = mService.deleteMember(memberId);
			if(result > 0 ) {
				
				//2.성공후 메인페이지로 이동하는데 세션 정보 파괴
				//한번만써도됨!!
				response.sendRedirect("/member/logout");
				
				//아래코드는 이미 로그아웃에 사용되기때문에 2번쓸필요가 없다!
				//아래코드는 주석처리함
				//1삭제 성공후 메인페이지로 이동하는데 세션 정보 파괴
				//session.invalidate();
				//response.sendRedirect("/");
			}else {
				//탈퇴 실패 에러페이지로 이동
				request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
				.forward(request, response);
			}
		}
	}
			
		
		

}
