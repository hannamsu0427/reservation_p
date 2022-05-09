package com.han.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.han.vo.MemberVO;

public interface MemberDao {	
	
	public Integer totalCount(MemberVO MemberVO);
	
	public Integer totalCountBranch(MemberVO MemberVO);
	
	public Integer totalCountconfirmYn(Map<String, Object> params);
	
	public MemberVO selectApplyInformation();
	
}
