package com.amity.hms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.amity.hms.beans.OutpassBean;
import com.amity.hms.beans.UserBean;
import com.amity.hms.service.HmsService;
import com.amity.hms.utils.SendEmail;

@Controller
public class WardenController {

	private static final Logger logger = LoggerFactory.getLogger(WardenController.class);
	
	@Autowired
	private HmsService hmsService;
	
	@RequestMapping(value = "getOutpass")
	public MappingJacksonJsonView getOutpass(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Get Outpass!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			String outpassStatus = request.getParameter("outpassStatus");
			
			List<OutpassBean> outpassList = hmsService.getOutpass(outpassStatus);
			view.addStaticAttribute("success", true);
			view.addStaticAttribute("totalCount", outpassList.size());
			view.addStaticAttribute("outpass", outpassList);
		} catch (Exception e) {
			view.addStaticAttribute("success", false);
			view.addStaticAttribute("message", "something wrong");
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "approveOutpass")
	public MappingJacksonJsonView approveOutpass(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Approve Outpass!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			
			Integer outpassId = new Integer(request.getParameter("outpassId"));
			System.out.println(outpassId);
			OutpassBean outpassBean = hmsService.getOutpass(outpassId);
			outpassBean.setOutpassStatus("WA");
			hmsService.updateOutpass(outpassBean);
			
			UserBean userBean = hmsService.loginCheck(outpassBean.getEnrollNo());
			
			
			String emailSubject = "Amity Approved OutPass: "+ userBean.getFirstName()+" : "+userBean.getEnrollNo();
			String emailBody = "Dear All, \n\n\n"+
								"The following Outpass Request is approved: \n\n"+
								"Student Name: "+userBean.getFirstName()+" "+userBean.getLastName()+"\n\n"+
								"Enrollment Number: "+userBean.getEnrollNo()+"\n\n"+
								"Outpass From Date: "+outpassBean.getFromDate()+"\n\n"+
								"Outpass To Date: "+outpassBean.getToDate()+"\n\n"+
								"Place of Travel: "+outpassBean.getPlace()+"\n\n"+
								"Reason: "+outpassBean.getReason()+"\n\n\n"+
								"Outpass ID: "+outpassBean.getOutpassId();
			
			SendEmail.send(emailSubject, emailBody, userBean.getStudentEmail());// Add Security email later...
			
			view.addStaticAttribute("success", true);
		} catch (Exception e) {
			view.addStaticAttribute("success", false);
			view.addStaticAttribute("message", "something wrong");
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "rejectOutpass")
	public MappingJacksonJsonView rejectOutpass(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Reject Outpass!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			
			Integer outpassId = new Integer(request.getParameter("outpassId"));
			System.out.println(outpassId);
			
			OutpassBean outpassBean = hmsService.getOutpass(outpassId);
			UserBean userBean = hmsService.loginCheck(outpassBean.getEnrollNo());

			// Send Email
			String emailSubject = "Amity Reject OutPass: "+ userBean.getFirstName()+" : "+userBean.getEnrollNo();
			String emailBody = "Dear All, \n\n\n"+
								"The following Outpass Request is rejected by the Warden: \n\n"+
								"Student Name: "+userBean.getFirstName()+" "+userBean.getLastName()+"\n\n"+
								"Enrollment Number: "+userBean.getEnrollNo()+"\n\n"+
								"Outpass From Date: "+outpassBean.getFromDate()+"\n\n"+
								"Outpass To Date: "+outpassBean.getToDate()+"\n\n"+
								"Place of Travel: "+outpassBean.getPlace()+"\n\n"+
								"Reason: "+outpassBean.getReason()+"\n\n"+
								"Outpass ID: "+outpassBean.getOutpassId()+"\n\n\n\n"+
								"Note: The record will be removed from the System.\nCreate a fresh request for outpass.";
			
			SendEmail.send(emailSubject, emailBody, userBean.getStudentEmail());
			
			hmsService.deleteOutpass(outpassBean);
			view.addStaticAttribute("success", true);
		} catch (Exception e) {
			view.addStaticAttribute("success", false);
			view.addStaticAttribute("message", "something wrong");
			e.printStackTrace();
		}
		return view;
	}
}