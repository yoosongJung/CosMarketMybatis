package com.cosmarket.memberInfo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cosmarket.memberInfo.model.service.MemberService;
import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/memberInfo/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/memberInfo/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		MemberService service = new MemberService();
		if(request.getParameter("memberType").equals("buyer")) {
			MemberBuyer memberBuyer = new MemberBuyer(memberId, memberPw);
			MemberBuyer mOne = service.selectCheckLoginBuyer(memberBuyer);
			if(mOne != null) {
				HttpSession session = request.getSession();
				session.setAttribute("memberId", mOne.getMemberId());
				session.setAttribute("memberName", mOne.getMemberName());
				session.setAttribute("memberType", "buyer");
				// 로그인 성공!
				request.setAttribute("msg", "로그인 성공!");
				request.setAttribute("url", "/index.jsp");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceSuccess.jsp");
				view.forward(request, response);
			} else {
				// 로그인 실패!
				request.setAttribute("msg", "로그인 정보 불일치");
				request.setAttribute("url", "/memberInfo/login.do");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp");
				view.forward(request, response);
			}
		} else {
			MemberSeller memberSeller = new MemberSeller(memberId, memberPw);
			MemberSeller mOne = service.selectCheckLoginSeller(memberSeller);
			if(mOne != null) {
				HttpSession session = request.getSession();
				session.setAttribute("memberId", mOne.getMemberId());
				session.setAttribute("memberName", mOne.getMemberName());
				session.setAttribute("memberType", "seller");
				// 로그인 성공!
				request.setAttribute("msg", "로그인 성공!");
				request.setAttribute("url", "/index.jsp");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceSuccess.jsp");
				view.forward(request, response);
			} else {
				// 로그인 실패!
				request.setAttribute("msg", "로그인 정보 불일치");
				request.setAttribute("url", "/memberInfo/login.do");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp");
				view.forward(request, response);
			}
		}
	}

}
