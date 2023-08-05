package com.cosmarket.memberInfo.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

public class MemberDAO {

	public int insertMemberBuyer(SqlSession session, MemberBuyer memberBuyer) {
		int result = session.insert("MemberMapper.insertMemberBuyer", memberBuyer);
		return result;
	}

	public int insertMemberSeller(SqlSession session, MemberSeller memberSeller) {
		int result = session.insert("MemberMapper.insertMemberSeller", memberSeller);
		return result;
	}

	public int updateBuyerMember(SqlSession session, MemberBuyer memberBuyer) {
		int result = session.update("MemberMapper.updateBuyerMember", memberBuyer);
		return result;
	}

	public int updateSellerMember(SqlSession session, MemberSeller memberSeller) {
		int result = session.update("MemberMapper.updateSellerMember", memberSeller);
		return result;
	}

	public int deleteMember(SqlSession session, String memberId, String memberType) {
		int result = 0;
		if(memberType.equals("buyer")) {
			result = session.delete("MemberMapper.deleteMemberBuyer", memberId);
		} else {
			result = session.delete("MemberMapper.deleteMemberSeller", memberId);
		}
		
		return result;
	}

	public MemberBuyer selectCheckLogin(SqlSession session, MemberBuyer memberBuyer) {
		MemberBuyer mOne = session.selectOne("MemberMapper.selectCheckLoginBuyer", memberBuyer);
		return mOne;
	}

	public MemberSeller selectCheckLogin(SqlSession session, MemberSeller memberSeller) {
		MemberSeller mOne = session.selectOne("MemberMapper.selectCheckLoginSeller", memberSeller);
		return mOne;
	}
	
	public MemberBuyer selectOneBuyerById(SqlSession session, String memberId) {
		MemberBuyer mOne = session.selectOne("MemberMapper.selectOneBuyerById", memberId);
		return mOne;
	}
	
	public MemberSeller selectOneSellerById(SqlSession session, String memberId) {
		MemberSeller mOne = session.selectOne("MemberMapper.selectOneSellerById", memberId);
		return mOne;
	}


}
