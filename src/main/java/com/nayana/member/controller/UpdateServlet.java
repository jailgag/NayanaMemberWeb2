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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/member/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * mypage.jsp에서 고치는것만 name값을 입력해줌!예시 
	 * <input type="email" value="${requestScope.member.email }" name="email" >
	 * 
	 * Member member = new Member(memberId, email, phone, address, hobby);//생성자 만들기!
		System.out.println(member.toString());
		System.out.println("test");
		콘솔창에 디버킹?하는법??
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// UPDATE MEMBER_TBL_SET EMAIL =? , PHONE=? , ADDRESS=?, HOBBY=? WHERE MEMBER_ID= ?;
		//쿼리문을 생각하고 거기에 맞춰서 밑에 작성!
		//mypage.jsp보면서!
		//~~~ =request.getParameter("memberId");
		//("memberId") 갈호안에 저값은 mypage.jsp에 name 값
		//예시!<input type="email" value="${requestScope.member.email }" name="email" >
		//String email  =request.getParameter("");
		//저기서 ()안에는 name값 email입력!!
		String memberId =request.getParameter("memberId");
		String email  =request.getParameter("email");
		String phone  =request.getParameter("phone");
		String address  =request.getParameter("address");
		String hobby  =request.getParameter("hobby");
		Member member = new Member(memberId, email, phone, address, hobby);//생성자 만들기!
		//업데이트 쳐줘요!Service에게~
		MemberService mService = new MemberService();
		int result = mService.updateMember(member);//
		if(result > 0) {
			//업데이트성공!!
			//마이페이지로 이동하고 싶으면 mypage.jsp를 보여줘여하고
			//mypage.jsp에는 디비에서 가져온 값이 필요하므로 다음과같이 코드를 작성
			//하지만 이미 MypageServlet에 해당코드가 작성되어있으므로
			//sendRedirect() 메소드를 이용해서 MypageServlet을 호출하는것으로 대체가능!!
			//4.아래코드를 사용!!!
			response.sendRedirect("/member/mypage");
			
			//아래코드는 이미mypageSevlet에서 사용하기때문에 대해서 위코드를사용
			//아래코드는 주석처리!!
			//3.member = mService.selectOneByid(memberId);
			//request.setAttribute("member", member);
			//request.getRequestDispatcher("/WEB-INF/views/member/mypage2.jsp")
			//.forward(request, response);
			
			//2.마이페이지로 이동시키는 코드 (문제점이있다 데이터가 안나오는문제점!)
			//request.getRequestDispatcher("/WEB-INF/views/member/mypage2.jsp")
			//.forward(request, response);
			
			//1.업데이트 성공 //메인페이지로이동코드아래!!
			//response.sendRedirect("/");
		}else {
			//업데이트 실패 에러페이지이동!
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
		}
	}

}
