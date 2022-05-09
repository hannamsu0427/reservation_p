package com.han.controller.cms;

import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.han.common.CommUtils;
import com.han.common.CommandMap;
import com.han.common.Config;
import com.han.common.CustomGenericException;
import com.han.common.PageUtil;
import com.han.dao.ReservationDao;
import com.han.dao.MemberDao;
import com.han.dao.ProcDao;
import com.han.vo.ReservationVO;
import com.han.vo.CustomerVO;
import com.han.vo.MemberVO;
import com.han.vo.ProcVO;
import com.han.vo.ResponseHeaderVO;
import com.han.vo.ResponseVO;

/*import com.han.service.TeacherService;
import com.han.vo.ResponseHeaderVO;
import com.han.vo.ResponseVO;*/

@Controller
public class CmsController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	MemberDao MemberDao; 
	
	@Autowired
	ReservationDao ReservationDao;
	
	@Autowired
	ProcDao ProcDao;
	
	@RequestMapping(value = "/cms/reservation/list.do")
	public ModelAndView reservationList(Map<String, Object> commandMap, HttpSession session, HttpServletRequest request, HttpServletResponse response
			,@RequestParam(defaultValue = "") String searchBy, @RequestParam(defaultValue = "") String searchValue
			,@RequestParam(defaultValue = "1") String pageNum, @RequestParam(defaultValue = "") String flag)
			throws Exception {
		
		ModelAndView mv = new ModelAndView("cms/reservation/list");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("searchBy", searchBy);
		params.put("searchValue", searchValue);
		
		System.out.println("1111");
		
		//페이징 처리
		Integer count = ReservationDao.selectPageCount(params);		
		Map<String, Object> pageMap = PageUtil.pageMap(count, pageNum, "20");
		mv.addObject("pageMap", pageMap);
		Integer startNum = Integer.valueOf(pageMap.get("startNum").toString());
		if(startNum != 0) {
			startNum = startNum-1;
		}
		params.put("startNum", startNum);
		params.put("endNum", pageMap.get("endNum"));
		
		List<ReservationVO> List = ReservationDao.selectReservationList(params);		
		
//		for(Integer i=0; i<List.size(); i++) { 
//			Integer seq = List.get(i).getseq();
//			params.put("seq", seq);			
//			CustomerVO data = ReservationDao.selectCustomerData(params);
//			List.get(i).settotalPrice(data.gettotalPrice());
//		}
		
		params.put("searchBy", searchBy);
		params.put("searchValue", searchValue);
		mv.addObject("flag", flag);
		mv.addObject("List", List);
		
		return mv;
	}
	
	@RequestMapping(value = "/cms/reservation/view.do")
	public ModelAndView viewCustomer(Map<String, Object> commandMap, HttpSession session, HttpServletRequest request, HttpServletResponse response
			, @RequestParam(defaultValue = "") String seq, @RequestParam(defaultValue = "") String action
			, @RequestParam(defaultValue = "") String flag)
			throws Exception {
		
		ModelAndView mv = new ModelAndView("customer/viewCustomer");
		Map<String, Object> CustomerDataParams = new HashMap<String, Object>();
		CustomerDataParams.put("seq", seq);
		ReservationVO customerData = ReservationDao.selectReservationData(CustomerDataParams);
		mv.addObject("customerData", customerData);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parent_seq", seq);
		List<ProcVO> ProcList = ProcDao.selectProcList(params);
		
		mv.addObject("flag", flag);
		mv.addObject("ProcList", ProcList);
		
		return mv;
	}
	
	@RequestMapping(value = "/cms/reservation/edit.do")
	public ModelAndView editCustomer(Map<String, Object> commandMap, HttpSession session, HttpServletRequest request, HttpServletResponse response
			, @RequestParam(defaultValue = "") String seq, @RequestParam(defaultValue = "") String action
			, @RequestParam(defaultValue = "") String flag)
			throws Exception {
		
		ModelAndView mv = new ModelAndView("customer/editCustomer");
		
		//오늘날짜 셋팅
		Date today = new Date();	        
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		mv.addObject("today", date.format(today).toString());
		
		if(!"addCustomer".equals(action)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("seq", seq);
			ReservationVO customerData = ReservationDao.selectReservationData(params);
			mv.addObject("customerData", customerData);
		}
		
		mv.addObject("flag", flag);
		mv.addObject("action", action);//updateCustomer, addCustomer
		return mv;
	}

	
	/*
	@SuppressWarnings("unchecked")
	@RequestMapping(headers = "Content-Type=application/json", value = "/dataSaveProc", method = RequestMethod.POST)
	public @ResponseBody ResponseVO dataSaveProc(@ModelAttribute CustomerVO CustomerVO, ProcVO ProcVO, @RequestBody HashMap<String, Object> map, HttpSession session) throws Exception {
		
		ResponseVO resVO = new ResponseVO();
		ResponseHeaderVO responseHeader = new ResponseHeaderVO();
		
		String errorType = ResponseHeaderVO.FAIL_MESSAGE;
		CustomerVO.setname(map.get("name").toString());
		CustomerVO.setphone1(map.get("phone1").toString());
		CustomerVO.setphone2(map.get("phone2").toString());
		CustomerVO.setphone3(map.get("phone3").toString());
		CustomerVO.setcontents(map.get("contents").toString());
		CustomerVO.setsex(map.get("sex").toString());
		String procYn = map.get("procYn").toString();
		
		if("Y".equals(procYn)) {
			ProcVO.setprocDate(map.get("procDate").toString());			
			ProcVO.setpractitionerCode(map.get("practitionerCode").toString());
			ProcVO.setprocExt(map.get("procExt").toString());		
		}			
		
		try {
			Integer customerChk = ReservationDao.selectTotalCount(CustomerVO);
			if(customerChk > 0) {
				errorType = ResponseHeaderVO.DUP_BRANCHSEQ_MESSAGE;
				throw new Exception();
			}
			
			//최초 등록시 시술정보도 입력 하도록 seq 값을 반환
			Integer seq = ReservationDao.saveProcCustomerData(CustomerVO);
			if("Y".equals(procYn)) {
				String code_price_array = map.get("code_price_array").toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
				ProcVO.setparentSeq(seq);
				String[] array = code_price_array.split(",");
				if(array.length > 1 ) {
					System.out.println("array : " + array);
					for(Integer i=0; i<array.length; i++) {
						ProcVO.setprocCode(array[i]);					
						Integer price = Integer.parseInt(array[i+1]);
						ProcVO.setprice(price);
						i++;
						ProcDao.saveProcData(ProcVO);
					}				
				}else {
					ProcVO.setprice(Integer.valueOf(map.get("price").toString()));
					ProcVO.setprocCode(map.get("procCode").toString());
					ProcDao.saveProcData(ProcVO);
				}
			}
			responseHeader.setCode(ResponseHeaderVO.SUCCESS);
		} catch (Exception e) {			
			responseHeader.setCode(ResponseHeaderVO.FAIL);
			responseHeader.setMessage(errorType);		
		}	
		
		resVO.setHeader(responseHeader);
		return resVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(headers = "Content-Type=application/json", value = "/dataUpdateProc", method = RequestMethod.POST)
	public @ResponseBody ResponseVO dataUpdateProc(@ModelAttribute CustomerVO CustomerVO, @RequestBody HashMap<String, Object> map, HttpSession session) 
			throws Exception {

		ResponseVO resVO = new ResponseVO();
		ResponseHeaderVO responseHeader = new ResponseHeaderVO();
		
		CustomerVO.setseq(Integer.valueOf(map.get("seq").toString()));
		CustomerVO.setname(map.get("name").toString());
		CustomerVO.setphone1(map.get("phone1").toString());
		CustomerVO.setphone2(map.get("phone2").toString());
		CustomerVO.setphone3(map.get("phone3").toString());
		CustomerVO.setcontents(map.get("contents").toString());
		CustomerVO.setsex(map.get("sex").toString());
		String errorType = "";
		
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("seq", map.get("seq"));
			CustomerVO customerData = ReservationDao.selectCustomerData(params);
			//업데이트시 기존 전화번호와 입력 전화번호가 다를경우 중복체크
			if(!CustomerVO.getphone1().equals(customerData.getphone1()) || !CustomerVO.getphone2().equals(customerData.getphone2()) || !CustomerVO.getphone3().equals(customerData.getphone3())) {
				Integer customerChk = ReservationDao.selectTotalCount(CustomerVO);
				if(customerChk > 0) {
					errorType = "overlap";
					throw new Exception();
				}
			}
			ReservationDao.updateProcCustomerData(CustomerVO);
			responseHeader.setCode(ResponseHeaderVO.SUCCESS);
		} catch (Exception e) {			
			responseHeader.setCode(ResponseHeaderVO.FAIL);
			if("overlap".equals(errorType)) {
				responseHeader.setMessage(ResponseHeaderVO.DUP_BRANCHSEQ_MESSAGE);
			}else {
				responseHeader.setMessage(ResponseHeaderVO.FAIL_MESSAGE);
			}	
		}	
		
		resVO.setHeader(responseHeader);
		return resVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(headers = "Content-Type=application/json", value = "/customer/dataDeleteProc", method = RequestMethod.POST)
	public @ResponseBody ResponseVO dataDeleteProc(@ModelAttribute CustomerVO CustomerVO, @RequestBody HashMap<String, Object> map, HttpSession session) 
			throws Exception {

		ResponseVO resVO = new ResponseVO();
		ResponseHeaderVO responseHeader = new ResponseHeaderVO();
		
		Integer seq = Integer.valueOf(map.get("seq").toString());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentSeq", seq);
		
		try {
			ReservationDao.deleteCustomerData(seq);
			ProcDao.deleteProcData(params);
			responseHeader.setCode(ResponseHeaderVO.SUCCESS);
		} catch (Exception e) {			
			responseHeader.setCode(ResponseHeaderVO.FAIL);
			responseHeader.setMessage(ResponseHeaderVO.FAIL_MESSAGE);
		}	
		
		resVO.setHeader(responseHeader);
		return resVO;
	}
	*/
	
	/*@RequestMapping(value = "/selectBranch.do")
	public ModelAndView result(Map<String, Object> commandMap, HttpSession session, HttpServletRequest request)
			throws Exception {
		ModelAndView mv = new ModelAndView("application/selectBranch");
		
		MemberVO map = MemberDao.selectApplyInformation();			
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");	        
        Date now = new Date();
        Date today = format.parse(format.format(now));
        Date startDay = format.parse( map.getrdateFrom() );
        Date endDay = format.parse( map.getrdateTo() );

        int compare = today.compareTo(startDay);
        
        System.out.println("compare : " + compare);

        if ( compare == -1 ) {
    		mv = new ModelAndView("/other/ready");            	
        }
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("name", session.getAttribute("name"));
		params.put("email", session.getAttribute("email"));
		params.put("yy", session.getAttribute("yy"));
		params.put("term", session.getAttribute("term"));
		//params.put("password", request.getParameter("password"));
		
		PersonalDataVO personalData = (PersonalDataVO) PersonalDataDao.selectPersonalData(params);
		
		List<PersonalDataVO> branchList = PersonalDataDao.selectPersonalDataBranch(params);
		
		mv.addObject("branchList", branchList);
		
		StringBuffer URL = request.getRequestURL();		
		String flag = URL.substring(URL.lastIndexOf("/")+1,URL.lastIndexOf(".do"));
		mv.addObject("flag", flag);
		
		return mv;
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(headers = "Content-Type=application/json", value = "/selectBranch/ajaxGoPersonalData", method = RequestMethod.POST)
	public @ResponseBody ResponseVO ajaxGoPersonalData(@RequestBody HashMap<String, Object> map, HttpSession session) throws Exception {

		ResponseVO resVO = new ResponseVO();
		ResponseHeaderVO responseHeader = new ResponseHeaderVO();
		
		try {
			session.setAttribute("applyNo", map.get("applyNo"));
			responseHeader.setCode(ResponseHeaderVO.SUCCESS);
		} catch (Exception e) {			
			responseHeader.setCode(ResponseHeaderVO.FAIL);
			responseHeader.setMessage(ResponseHeaderVO.FAIL_MESSAGE);
		}	
		
		resVO.setHeader(responseHeader);
		return resVO;
	}*/
}
