package com.cosmarket.memberInfo.model.service;

import java.sql.Connection;

import com.cosmarket.memberInfo.model.dao.MemberDAO;
import com.cosmarket.memberInfo.model.vo.MemberBuyer;
import com.cosmarket.memberInfo.model.vo.MemberSeller;

import commonSession.JDBCTemplate;

public class MemberService {
	
	JDBCTemplate jdbcTemplate;
	MemberDAO mDao;
	
	public MemberService() {
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO();
	}

	public int insertMemberBuyer(MemberBuyer memberBuyer) {
		Connection conn = jdbcTemplate.createConnection();
		int result = mDao.insertMemberBuyer(conn, memberBuyer);
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int insertMemberSeller(MemberSeller memberSeller) {
		Connection conn = jdbcTemplate.createConnection();
		int result = mDao.insertMemberSeller(conn, memberSeller);
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public MemberBuyer selectCheckLoginBuyer(MemberBuyer memberBuyer) {
		Connection conn = jdbcTemplate.createConnection();
		MemberBuyer mOne = mDao.selectCheckLogin(conn, memberBuyer);
		jdbcTemplate.close(conn);
		return mOne;
	}

	public MemberSeller selectCheckLoginSeller(MemberSeller memberSeller) {
		Connection conn = jdbcTemplate.createConnection();
		MemberSeller mOne = mDao.selectCheckLogin(conn, memberSeller);
		jdbcTemplate.close(conn);
		return mOne;
	}

	public int deleteMember(String memberId, String memberType) {
		Connection conn = jdbcTemplate.createConnection();
		int result = mDao.deleteMember(conn, memberId, memberType);
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}
	
	public int updateBuyerMember(MemberBuyer memberBuyer) {
		Connection conn = jdbcTemplate.createConnection();
		int result = mDao.updateBuyerMember(conn, memberBuyer);
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int updateSellerMember(MemberSeller memberSeller) {
		Connection conn = jdbcTemplate.createConnection();
		int result = mDao.updateSellerMember(conn, memberSeller);
		if(result > 0) {
			// 성공 - 커밋
			jdbcTemplate.commit(conn);
		} else {
			// 실패 - 롤백
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public MemberBuyer selectOneBuyerById(String memberId) {
		Connection conn = jdbcTemplate.createConnection();
		MemberBuyer memberBuyer = mDao.selectOneBuyerById(conn, memberId);
		jdbcTemplate.close(conn);
		return memberBuyer;
	}

	public MemberSeller selectOneSellerById(String memberId) {
		Connection conn = jdbcTemplate.createConnection();
		MemberSeller memberSeller = mDao.selectOneSellerById(conn, memberId);
		jdbcTemplate.close(conn);
		return memberSeller;
	}


}
