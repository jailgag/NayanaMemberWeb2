package com.nayana.member.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.nayana.member.model.service.MemberService;
import com.nayana.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * login.jsp에서 post로 해서 여기로 dopost가 동작이 되는데
	 *  http://local~~:8888 (여긴7777로!!)/member/login url로 동작이됨!
	 *  post방식이라 쿼리스트링이 안보임!! 
	 *  아래 String memberId = request.getParameter("memberId");에서
	 *  ("memberId")부분이 login.jsp에 아이디 input태그에 ***name부분과 똑같아야한다!!******
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//아래코드 () 안에 적는건 login.jsp에
		// <input typt="text" name="memberId">
		//name="memberID" <-"" 안에 있는것을 적어준다!!
		MemberService mService = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberPw =request.getParameter("memberPw");
		Member member = new Member(memberId,memberPw);
		//selectOneByLogin부분에 컨트롤누르고 클릭하면 이동!!
		member = mService.selectOneByLogin(member);
		if(member != null) {
			//아래코드 누락되어 로그인버튼 없어지지 않았다?
			HttpSession session = request.getSession(); //<--서버측 연결(저장공간?)
			session.setAttribute("member", member);
			//로그인성공->메인페이지로 이동!  예를들어 게시판으로 이동한다면
			//http://localhost:8888/	http://localhost:8888/border~
			//url 을 이용한 페이지 이동시 response 객체사용
		
			response.sendRedirect("/"); //sendRedirect 메소드호출!
		}else {
			//로그인 실패!! 에러 페이지로 이동!!
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
			
		}
	}

}
