package com.nayana.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nayana.member.model.service.MemberService;
import com.nayana.member.model.vo.Member;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/member/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String memberName = request.getParameter("memberName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));//강제형변환!!
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String hobby = request.getParameter("hobby");
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		MemberService mService = new MemberService();
		int result = mService.insertMember(member); //insertMember메소드 만들러감!!서비스로이동!!
		if(result > 0) { //<--result가 0보다 크면!!
			//회원가입성공!!-> 로그인페이지로 이동! (~~member/login.jsp) -->/member/login url을가지고 하기때문에 아래코드는 지우고 다시!!주석처리함!! 
			//request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
			//.forward(request, response);
			//아래 코드쓸시 여기선 sendRedirect로 이용시 /가아닌 /member/login으로 적어줌!!로그인페이지로 이동할거니깐!!
			response.sendRedirect("/member/login");  //get 요청이니깐 doget이 동작함!
		}else {
			//회원가입실패!->에러페이지이동(~~views/common/error/error.jsp)
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
		}
	}

}
