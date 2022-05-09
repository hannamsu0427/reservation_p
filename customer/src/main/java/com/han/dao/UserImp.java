package com.han.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.han.vo.UserVO;

public class UserImp extends SqlMapClientDaoSupport implements UserDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer totalCount(UserVO UserVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject("User.totalCount", UserVO);
	}
	
	@Override
	public UserVO selectUserInfo(Map<String, Object> params) {
		return (UserVO) getSqlMapClientTemplate().queryForObject("User.selectUserInfo", params);	
	}
	
	/*public Integer totalCountBranch(MemberVO MemberVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Member.totalCountBranch", MemberVO);
	}
	
	public Integer totalCountconfirmYn(Map<String, Object> params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Member.totalCountconfirmYn", params);
	}
	
	@Override
	public MemberVO selectApplyInformation() {
		return (MemberVO) getSqlMapClientTemplate().queryForObject("Member.selectApplyInformation");	
	}*/
	
	
}
