package com.han.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.han.vo.ReservationVO;

public interface ReservationDao {		
	
	public Integer saveProcReservationData(ReservationVO ReservationVO);
	
	public ReservationVO updateProcReservationData(ReservationVO ReservationVO);	
	
	public List<ReservationVO> selectReservationList(Map<String, Object> params);
	
	public ReservationVO selectReservationData(Map<String, Object> params);
	
	public Integer deleteReservationData(Integer seq);
	
	public Integer selectTotalCount(ReservationVO ReservationVO);
	
	public Integer selectPageCount(Map<String, Object> params);
	
	
	/*public PersonalDataVO selectDepName(Map<String, Object> params);
	
	public PersonalDataVO selectUnivName(Map<String, Object> params);	
	
	public List<PersonalDataVO> selectNationalList();
	
	public List<PersonalDataVO> selectUnivList(Map<String, Object> params);
	
	public List<PersonalDataVO> selectDepList(Map<String, Object> map);
	
	public List<PersonalDataVO> selectFieldList(Map<String, Object> map);
	
	public List<PersonalDataVO> selectPersonalDataBranch(Map<String, Object> params);
	
	public Integer selectCountPersonalData(Map<String, Object> params);
	
	public Integer selectCountpersonalDataBranchSeq(Map<String, Object> params);
	
	public Integer selectMaxPersonalDataConfirnNo(Map<String, Object> params);*/
	
	/*public void updateProcPersonalData(PersonalDataVO PersonalDataVO);
	
	public PersonalDataVO selectPersonalData(Map<String, Object> params);	
	
	public List<PersonalDataVO> selectAjaxDepList(Map<String, Object> map);
	
	public List<PersonalDataVO> selectAjaxFieldList(Map<String, Object> map);
	
	public PersonalDataVO selectUnivDep(Map<String, Object> params);
	
	public void updateProcPersonalDocData(Map<String, Object> params);*/
	
}
