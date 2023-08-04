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
 * Servlet implementation class UpdateController
 */
@WebServlet("/member/update.do")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
			int result = 0;
			String memberPwNew = request.getParameter("memberPwNew");
			String memberPwNewCheck = request.getParameter("memberPwNewCheck");
			String memberEmail = request.getParameter("memberEmail");
			String memberPhone = request.getParameter("memberPhone");
			String memberPostcode = request.getParameter("memberPostcode");
			String memberAddress = request.getParameter("memberAddress");
			String memberAddressDetail = request.getParameter("memberAddressDetail");
			if(mOneBuyer != null) {
				MemberBuyer memberBuyer = new MemberBuyer(memberId, memberPwNew, memberPwNewCheck, memberEmail, memberPhone, memberPostcode, memberAddress, memberAddressDetail);
				result = service.updateBuyerMember(memberBuyer);
			} else {
				String memberBank = request.getParameter("memberBank");
				String memberAccount = request.getParameter("memberAccount");
				MemberSeller memberSeller = new MemberSeller(memberId, memberPwNew, memberPwNewCheck, memberEmail, memberPhone, memberPostcode, memberAddress, memberAddressDetail, memberBank, memberAccount);
				result = service.updateSellerMember(memberSeller);
			}
			if(result > 0) {
				// 성공
				request.setAttribute("msg", "회원정보 수정 성공");
				request.setAttribute("url", "/member/myInfo.do?memberId="+memberId+"&memberType="+ memberType);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceSuccess.jsp");
				view.forward(request, response);
			} else {
				// 실패
				request.setAttribute("msg", "회원정보 수정 실패");
				request.setAttribute("url", "/member/myInfo.do?memberId="+memberId+"&memberType="+ memberType);
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp");
				view.forward(request, response);
			}
		} else {
			request.setAttribute("msg", "현재 비밀번호 불일치");
			request.setAttribute("url", "/member/myInfo.do?memberId="+memberId+"&memberType="+ memberType);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp");
			view.forward(request, response);
		}
	}

}
