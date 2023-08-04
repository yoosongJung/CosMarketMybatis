package com.cosmarket.memberInfo.controller;

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
 * Servlet implementation class RegisterController
 */
@WebServlet("/memberInfo/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/memberInfo/form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberService service = new MemberService();
		int result = 0;
		if(request.getParameter("memberType").equals("buyer")) { //일반회원
			String memberName = request.getParameter("memberName");
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			String memberPwCheck = request.getParameter("memberPwCheck");
			String memberEmail = request.getParameter("memberEmail");
			String memberPhone = request.getParameter("memberPhone");
			String memberPostcode = request.getParameter("memberPostcode");
			String memberAddress = request.getParameter("memberAddress");
			String memberAddressDetail = request.getParameter("memberAddressDetail");
			MemberBuyer memberBuyer = new MemberBuyer(memberName, memberId, memberPw, memberPwCheck, memberEmail, memberPhone, memberPostcode, memberAddress, memberAddressDetail);
			result = service.insertMemberBuyer(memberBuyer);
			
		} else { // 사업자회원
			String sellerNo = request.getParameter("sellerNo");
			String memberName = request.getParameter("memberName");
			String memberBank = request.getParameter("memberBank");
			String memberAccount = request.getParameter("memberAccount");
			String memberId = request.getParameter("memberId");
			String memberPw = request.getParameter("memberPw");
			String memberPwCheck = request.getParameter("memberPwCheck");
			String memberEmail = request.getParameter("memberEmail");
			String memberPhone = request.getParameter("memberPhone");
			String memberPostcode = request.getParameter("memberPostcode");
			String memberAddress = request.getParameter("memberAddress");
			String memberAddressDetail = request.getParameter("memberAddressDetail");
			MemberSeller memberSeller = new MemberSeller(sellerNo, memberName, memberBank, memberAccount, memberId, memberPw, memberPwCheck, memberEmail, memberPhone, memberPostcode, memberAddress, memberAddressDetail);
			result = service.insertMemberSeller(memberSeller);
		}
		if(result > 0) {
			// 성공하면 성공 페이지로 이동
			request.setAttribute("msg", "회원가입 성공했어요.");
			request.setAttribute("url", "/memberInfo/success.do");
			request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceSuccess.jsp")
			.forward(request, response);
		}else {
			// 실패
			request.setAttribute("msg", "회원가입 실패.");
			request.setAttribute("url", "/memberInfo/register.do");
			request.getRequestDispatcher("/WEB-INF/views/commonDisplay/serviceFailed.jsp")
			.forward(request, response);
		}
	}

}
