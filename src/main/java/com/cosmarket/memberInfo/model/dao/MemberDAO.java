package com.cosmarket.memberInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

public class MemberDAO {

	public int insertMemberBuyer(Connection conn, MemberBuyer memberBuyer) {
		String query = "INSERT INTO MEMBERBUYER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberBuyer.getMemberName());
			pstmt.setString(2, memberBuyer.getMemberId());
			pstmt.setString(3, memberBuyer.getMemberPw());
			pstmt.setString(4, memberBuyer.getMemberPwCheck());
			pstmt.setString(5, memberBuyer.getMemberEmail());
			pstmt.setString(6, memberBuyer.getMemberPhone());
			pstmt.setString(7, memberBuyer.getMemberPostcode());
			pstmt.setString(8, memberBuyer.getMemberAddress());
			pstmt.setString(9, memberBuyer.getMemberAddressDetail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertMemberSeller(Connection conn, MemberSeller memberSeller) {
		String query = "INSERT INTO MEMBERSELLER_TBL VALUES(?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberSeller.getSellerNo());
			pstmt.setString(2, memberSeller.getMemberName());
			pstmt.setString(3, memberSeller.getMemberBank());
			pstmt.setString(4, memberSeller.getMemberAccount());
			pstmt.setString(5, memberSeller.getMemberId());
			pstmt.setString(6, memberSeller.getMemberPw());
			pstmt.setString(7, memberSeller.getMemberPwCheck());
			pstmt.setString(8, memberSeller.getMemberEmail());
			pstmt.setString(9, memberSeller.getMemberPhone());
			pstmt.setString(10, memberSeller.getMemberPostcode());
			pstmt.setString(11, memberSeller.getMemberAddress());
			pstmt.setString(12, memberSeller.getMemberAddressDetail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public MemberBuyer selectCheckLogin(Connection conn, MemberBuyer memberBuyer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBERBUYER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		MemberBuyer mOne = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberBuyer.getMemberId());
			pstmt.setString(2, memberBuyer.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mOne = rsetToMemberBuyer(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mOne;
	}

	public MemberSeller selectCheckLogin(Connection conn, MemberSeller memberSeller) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBERSELLER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		MemberSeller mOne = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberSeller.getMemberId());
			pstmt.setString(2, memberSeller.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mOne = rsetToMemberSeller(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mOne;
	}
	
	public int deleteMember(Connection conn, String memberId, String memberType) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "";
		if(memberType.equals("buyer")) {
			query = "DELETE FROM MEMBERBUYER_TBL WHERE MEMBER_ID = ?";
		} else {
			query = "DELETE FROM MEMBERSELLER_TBL WHERE MEMBER_ID = ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateBuyerMember(Connection conn, MemberBuyer memberBuyer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBERBUYER_TBL SET MEMBER_PW = ?, MEMBER_PW_CHECK = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_POSTCODE = ?, MEMBER_ADDRESS = ?, MEMBER_ADDRESS_DETAIL = ? WHERE MEMBER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberBuyer.getMemberPw());
			pstmt.setString(2, memberBuyer.getMemberPwCheck());
			pstmt.setString(3, memberBuyer.getMemberEmail());
			pstmt.setString(4, memberBuyer.getMemberPhone());
			pstmt.setString(5, memberBuyer.getMemberPostcode());
			pstmt.setString(6, memberBuyer.getMemberAddress());
			pstmt.setString(7, memberBuyer.getMemberAddressDetail());
			pstmt.setString(8, memberBuyer.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateSellerMember(Connection conn, MemberSeller memberSeller) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBERSELLER_TBL SET MEMBER_BANK = ?, MEMBER_ACCOUNT = ?, MEMBER_PW = ?, MEMBER_PW_CHECK = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_POSTCODE = ?, MEMBER_ADDRESS = ?, MEMBER_ADDRESS_DETAIL = ? WHERE MEMBER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberSeller.getMemberBank());
			pstmt.setString(2, memberSeller.getMemberAccount());
			pstmt.setString(3, memberSeller.getMemberPw());
			pstmt.setString(4, memberSeller.getMemberPwCheck());
			pstmt.setString(5, memberSeller.getMemberEmail());
			pstmt.setString(6, memberSeller.getMemberPhone());
			pstmt.setString(7, memberSeller.getMemberPostcode());
			pstmt.setString(8, memberSeller.getMemberAddress());
			pstmt.setString(9, memberSeller.getMemberAddressDetail());
			pstmt.setString(10, memberSeller.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public MemberBuyer selectOneBuyerById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBERBUYER_TBL WHERE MEMBER_ID = ?";
		MemberBuyer mOne = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mOne = rsetToMemberBuyer(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mOne;
	}
	
	public MemberSeller selectOneSellerById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBERSELLER_TBL WHERE MEMBER_ID = ?";
		MemberSeller mOne = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mOne = rsetToMemberSeller(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
