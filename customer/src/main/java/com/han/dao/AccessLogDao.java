package com.han.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.han.vo.MemberVO;

public interface AccessLogDao {	
	
	void insertProcAccessLogData(Map<String, Object> params);
	
}

	
