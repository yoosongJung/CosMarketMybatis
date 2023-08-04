package com.cosmarket.member.common.myPage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cosmarket.memberInfo.model.service.MemberService;
import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

/**
 * Servlet implementation class DeleteMemberController
 */
@WebServlet("/member/delete.do")
public class DeleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/common/secession.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		MemberBuyer mOneBuyer = null;
		MemberSeller mOneSeller = null;
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberType = request.getParameter("memberType");
		if(request.getParameter("memberType").equals("buyer")) {
			MemberBuyer memberBuyer = new MemberBuyer(memberId, memberPw);
			mOneBuyer = service.selectCheckLoginBuyer(memberBuyer);
		} else {
			MemberSeller memberSeller = new MemberSeller(memberId, memberPw);
			mOneSeller = service.selectCheckLoginSeller(memberSeller);
		}
		if(mOneBuyer != null || mOneSeller != null) {
			int result = service.deleteMember(memberId, memberType);
			if(result > 0) {
				// 성공
				request.setAttribute("msg", "회원탈퇴 성공");
				request.setAttribute("url", "/memberInfo/logout.do");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceSuccess.jsp");
				view.forward(request, response);
			} else {
				// 실패
				request.setAttribute("msg", "회원탈퇴 실패(비밀번호 불일치)");
				request.setAttribute("url", "/member/delete.do");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp");
				view.forward(request, response);
			}
		} else {
			// 실패
			request.setAttribute("msg", "회원탈퇴 실패(비밀번호 불일치)");
			request.setAttribute("url", "/member/delete.do");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp");
			view.forward(request, response);
		}
	}

}
