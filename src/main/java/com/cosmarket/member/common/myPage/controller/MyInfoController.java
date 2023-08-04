package com.cosmarket.member.common.myPage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosmarket.memberInfo.model.service.MemberService;
import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

/**
 * Servlet implementation class MyInfoController
 */
@WebServlet("/member/myInfo.do")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberType = request.getParameter("memberType");
		if(memberType.equals("buyer")) {
			MemberBuyer memberBuyer = service.selectOneBuyerById(memberId);
			request.setAttribute("member", memberBuyer);
		} else {
			MemberSeller memberSeller = service.selectOneSellerById(memberId);
			request.setAttribute("member", memberSeller);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/member/common/myPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
