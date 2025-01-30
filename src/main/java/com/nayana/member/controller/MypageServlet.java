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
 * Servlet implementation class MypageServlet
 */
@WebServlet("/member/mypage")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * *****loginServlet에 session.SetAttribute("member",member)랑 
	 * 
	 * mypage에 !Member sMember = (Member)session.getAttribute("member");
	 * 에 .getAttribute("member"); ()갈호안에 "member"랑 같아야한다!!***
	 * 이내용 서블릿3일차-3(마이페이지 이어서 26분짜리에 있음!!!중요한내용 더 있음!!
	 * 반드시 request.setAttribute("member", member); 이코드를 써야한다?!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberService mService = new MemberService();
		HttpSession session = request.getSession();
		Member sMember = (Member)session.getAttribute("member"); //임포트확인할것
		String memberId = sMember.getMemberId();
		Member member = mService.selectOneByid(memberId); //id소문자로 되어있음!!확인!!
		if(member != null) {
			//데이터가 존재하면 마이페이지.jsp로 보냄
			request.setAttribute("member", member);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/mypage2.jsp");
			view.forward(request, response);
		}else {
			//데이터 없음!!
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
		}
	}

}
