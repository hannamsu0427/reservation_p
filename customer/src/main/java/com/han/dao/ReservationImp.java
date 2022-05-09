package com.han.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.han.vo.ReservationVO;


public class ReservationImp extends SqlMapClientDaoSupport implements ReservationDao {
	
	@SuppressWarnings("unchecked")
	
	public Integer saveProcReservationData(ReservationVO ReservationVO) {
		return (Integer) getSqlMapClientTemplate().insert("Reservation.saveProcReservationData", ReservationVO);
	}
	
	public ReservationVO updateProcReservationData(ReservationVO ReservationVO) {
		return (ReservationVO) getSqlMapClientTemplate().insert("Reservation.updateProcReservationData", ReservationVO);
	}
	
	@Override
	public List<ReservationVO> selectReservationList(Map<String, Object> params) {
		return (List<ReservationVO>) getSqlMapClientTemplate().queryForList("Reservation.selectReservationList", params);		
	}
	
	@Override
	public ReservationVO selectReservationData(Map<String, Object> params) {
		return (ReservationVO) getSqlMapClientTemplate().queryForObject("Reservation.selectReservationData", params);	
	}
	
	@Override
	public Integer deleteReservationData(Integer seq) {
		return getSqlMapClientTemplate().delete("Reservation.deleteReservationData", seq);
	}
	
	@Override
	public Integer selectTotalCount(ReservationVO ReservationVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Reservation.totalCount", ReservationVO);
	}
	
	@Override
	public Integer selectPageCount(Map<String, Object> params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("Reservation.pageCount", params);
	}
	/*
	
	@Override
	public PersonalDataVO selectDepName(Map<String, Object> params) {
		return (PersonalDataVO) getSqlMapClientTemplate().queryForObject("PersonalData.selectDepName", params);	
	}
	
	@Override
	public PersonalDataVO selectUnivName(Map<String, Object> params) {
		return (PersonalDataVO) getSqlMapClientTemplate().queryForObject("PersonalData.selectUnivName", params);	
	}
	
	@Override
	public List<PersonalDataVO> selectNationalList() {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectNationalList");		
	}
	
	@Override
	public List<PersonalDataVO> selectUnivList(Map<String, Object> params) {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectunivList", params);		
	}
	
	@Override
	public List<PersonalDataVO> selectDepList(Map<String, Object> map) {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectDepList", map);		
	}
	
	@Override
	public List<PersonalDataVO> selectFieldList(Map<String, Object> map) {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectFieldList", map);		
	}
	
	@Override
	public List<PersonalDataVO> selectPersonalDataBranch(Map<String, Object> params) {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectPersonalDataBranch", params);		
	}
	
	
	
	public void updateProcPersonalData(PersonalDataVO PersonalDataVO) {
		getSqlMapClientTemplate().insert("PersonalData.updateProcPersonalData", PersonalDataVO);
	}
	
	@Override
	public Integer selectCountPersonalData(Map<String, Object> params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("PersonalData.selectCountpersonalData", params);
	}
	
	@Override
	public Integer selectCountpersonalDataBranchSeq(Map<String, Object> params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("PersonalData.selectCountpersonalDataBranchSeq", params);
	}
	
	@Override
	public Integer selectMaxPersonalDataConfirnNo(Map<String, Object> params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("PersonalData.selectMaxPersonalDataConfirnNo", params);
	}	
	
	@Override
	public PersonalDataVO selectPersonalData(Map<String, Object> params) {
		return (PersonalDataVO) getSqlMapClientTemplate().queryForObject("PersonalData.selectPersonalData", params);	
	}
	
	@Override
	public List<PersonalDataVO> selectAjaxDepList(Map<String, Object> map) {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectAjaxDepList", map);		
	}
	
	@Override
	public List<PersonalDataVO> selectAjaxFieldList(Map<String, Object> map) {
		return (List<PersonalDataVO>) getSqlMapClientTemplate().queryForList("PersonalData.selectAjaxFieldList", map);		
	}
	
	@Override
	public PersonalDataVO selectUnivDep(Map<String, Object> params) {
		return (PersonalDataVO) getSqlMapClientTemplate().queryForObject("PersonalData.selectUnivDep", params);
	}
	
	@Override
	public void updateProcPersonalDocData(Map<String, Object> params) {
		getSqlMapClientTemplate().update("PersonalData.updateProcPersonalDocData", params);
	}*/
}
