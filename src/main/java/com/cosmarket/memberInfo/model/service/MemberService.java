package com.cosmarket.memberInfo.model.service;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;

import com.cosmarket.memberInfo.model.dao.MemberDAO;
import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

import commonSession.SqlSessionTemplate;


public class MemberService {
	
	MemberDAO mDao;
	
	public MemberService() {
		mDao = new MemberDAO();
	}

	public int insertMemberBuyer(MemberBuyer memberBuyer) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.insertMemberBuyer(session, memberBuyer);
		if(result > 0) {
			// 성공 - 커밋
			session.commit();
		} else {
			// 실패 - 롤백
			session.rollback();
		}
		session.close();
		return result;
	}

	public int insertMemberSeller(MemberSeller memberSeller) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.insertMemberSeller(session, memberSeller);
		if(result > 0) {
			// 성공 - 커밋
			session.commit();
		} else {
			// 실패 - 롤백
			session.rollback();
		}
		session.close();
		return result;
	}

	public MemberBuyer selectCheckLoginBuyer(MemberBuyer memberBuyer) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		MemberBuyer mOne = mDao.selectCheckLogin(session, memberBuyer);
		session.close();
		return mOne;
	}

	public MemberSeller selectCheckLoginSeller(MemberSeller memberSeller) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		MemberSeller mOne = mDao.selectCheckLogin(session, memberSeller);
		session.close();
		return mOne;
	}

	public int deleteMember(String memberId, String memberType) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.deleteMember(session, memberId, memberType);
		if(result > 0) {
			// 성공 - 커밋
			session.commit();
		} else {
			// 실패 - 롤백
			session.rollback();
		}
		session.close();
		return result;
	}
	
	public int updateBuyerMember(MemberBuyer memberBuyer) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.updateBuyerMember(session, memberBuyer);
		if(result > 0) {
			// 성공 - 커밋
			session.commit();
		} else {
			// 실패 - 롤백
			session.rollback();
		}
		session.close();
		return result;
	}

	public int updateSellerMember(MemberSeller memberSeller) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = mDao.updateSellerMember(session, memberSeller);
		if(result > 0) {
			// 성공 - 커밋
			session.commit();
		} else {
			// 실패 - 롤백
			session.rollback();
		}
		session.close();
		return result;
	}

	public MemberBuyer selectOneBuyerById(String memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		MemberBuyer memberBuyer = mDao.selectOneBuyerById(session, memberId);
		session.close();
		return memberBuyer;
	}

	public MemberSeller selectOneSellerById(String memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		MemberSeller memberSeller = mDao.selectOneSellerById(session, memberId);
		session.close();
		return memberSeller;
	}


}
