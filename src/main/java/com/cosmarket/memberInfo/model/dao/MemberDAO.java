package com.cosmarket.memberInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

public class MemberDAO {

	public int insertMemberBuyer(SqlSession session, MemberBuyer memberBuyer) {
		String query = "INSERT INTO MEMBERBUYER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		int result = 0;
		
		return result;
	}

	public int insertMemberSeller(SqlSession session, MemberSeller memberSeller) {
		String query = "INSERT INTO MEMBERSELLER_TBL VALUES(?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)";
		int result = 0;
		
		return result;
	}

	public MemberBuyer selectCheckLogin(SqlSession session, MemberBuyer memberBuyer) {
		String query = "SELECT * FROM MEMBERBUYER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		MemberBuyer mOne = null;
		
		return mOne;
	}

	public MemberSeller selectCheckLogin(SqlSession session, MemberSeller memberSeller) {
		String query = "SELECT * FROM MEMBERSELLER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		MemberSeller mOne = null;
		
		return mOne;
	}
	
	public int deleteMember(SqlSession session, String memberId, String memberType) {
		int result = 0;
		String query = "";
		if(memberType.equals("buyer")) {
			query = "DELETE FROM MEMBERBUYER_TBL WHERE MEMBER_ID = ?";
		} else {
			query = "DELETE FROM MEMBERSELLER_TBL WHERE MEMBER_ID = ?";
		}
		
		return result;
	}

	public int updateBuyerMember(SqlSession session, MemberBuyer memberBuyer) {
		int result = 0;
		String query = "UPDATE MEMBERBUYER_TBL SET MEMBER_PW = ?, MEMBER_PW_CHECK = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_POSTCODE = ?, MEMBER_ADDRESS = ?, MEMBER_ADDRESS_DETAIL = ? WHERE MEMBER_ID = ?";
		
		return result;
	}

	public int updateSellerMember(SqlSession session, MemberSeller memberSeller) {
		int result = 0;
		String query = "UPDATE MEMBERSELLER_TBL SET MEMBER_BANK = ?, MEMBER_ACCOUNT = ?, MEMBER_PW = ?, MEMBER_PW_CHECK = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_POSTCODE = ?, MEMBER_ADDRESS = ?, MEMBER_ADDRESS_DETAIL = ? WHERE MEMBER_ID = ?";
		
		return result;
	}

	public MemberBuyer selectOneBuyerById(SqlSession session, String memberId) {
		String query = "SELECT * FROM MEMBERBUYER_TBL WHERE MEMBER_ID = ?";
		MemberBuyer mOne = null;
		
		return mOne;
	}
	
	public MemberSeller selectOneSellerById(SqlSession session, String memberId) {
		String query = "SELECT * FROM MEMBERSELLER_TBL WHERE MEMBER_ID = ?";
		MemberSeller mOne = null;
		
		return mOne;
	}

	private MemberBuyer rsetToMemberBuyer(ResultSet rset) throws SQLException {
		MemberBuyer memberBuyer = new MemberBuyer();
		memberBuyer.setMemberName(rset.getString("MEMBER_NAME"));
		memberBuyer.setMemberId(rset.getString("MEMBER_ID"));
		memberBuyer.setMemberPw(rset.getString("MEMBER_PW"));
		memberBuyer.setMemberPwCheck(rset.getString("MEMBER_PW_CHECK"));
		memberBuyer.setMemberEmail(rset.getString("MEMBER_EMAIL"));
		memberBuyer.setMemberPhone(rset.getString("MEMBER_PHONE"));
		memberBuyer.setMemberPostcode(rset.getString("MEMBER_POSTCODE"));
		memberBuyer.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
		memberBuyer.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
		memberBuyer.setMemberEnrollDate(rset.getTimestamp("MEMBER_ENROLL_DATE"));
		
		return memberBuyer;
	}
	
	private MemberSeller rsetToMemberSeller(ResultSet rset) throws SQLException {
		MemberSeller memberSeller = new MemberSeller();
		memberSeller.setSellerNo(rset.getString("SELLER_NO"));
		memberSeller.setMemberName(rset.getString("MEMBER_NAME"));
		memberSeller.setMemberBank(rset.getString("MEMBER_BANK"));
		memberSeller.setMemberAccount(rset.getString("MEMBER_ACCOUNT"));
		memberSeller.setMemberId(rset.getString("MEMBER_ID"));
		memberSeller.setMemberPw(rset.getString("MEMBER_PW"));
		memberSeller.setMemberPwCheck(rset.getString("MEMBER_PW_CHECK"));
		memberSeller.setMemberEmail(rset.getString("MEMBER_EMAIL"));
		memberSeller.setMemberPhone(rset.getString("MEMBER_PHONE"));
		memberSeller.setMemberPostcode(rset.getString("MEMBER_POSTCODE"));
		memberSeller.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
		memberSeller.setMemberAddressDetail(rset.getString("MEMBER_ADDRESS_DETAIL"));
		memberSeller.setMemberEnrollDate(rset.getTimestamp("MEMBER_ENROLL_DATE"));
		return memberSeller;
	}


}
