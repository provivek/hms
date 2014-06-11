package com.amity.hms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.amity.hms.beans.OutpassBean;
import com.amity.hms.beans.UserBean;
import com.amity.hms.service.HmsService;
import com.amity.hms.utils.SendEmail;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private HmsService hmsService;
	
	@RequestMapping(value = "apply.outpass", method = RequestMethod.POST)
	public MappingJacksonJsonView applyOutpass(HttpServletRequest request, HttpServletResponse response, @ModelAttribute OutpassBean outpassBean) {
		logger.info("Apply Outpass!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			HttpSession session = request.getSession();
			if(session.isNew()) {
				logger.info("New Session");
			} else {
				logger.info("Old Session");
			}
			UserBean userBean = (UserBean) session.getAttribute("user");
			
			if(userBean != null) {
				outpassBean.setEnrollNo(userBean.getEnrollNo());
				outpassBean.setOutpassStatus("NEW");
				OutpassBean ob = hmsService.createOutpass(outpassBean);
				
				// Send Email for parents Approval
				System.out.println(ob.getOutpassId());
				String emailSubject = "Amity Request For OutPass: "+ userBean.getFirstName()+" : "+userBean.getEnrollNo();
				String emailBody = "Dear reader, \n\n\n"+
									"Greetings from Amity University \n\n"+
									"Outpass Request: \n\n\n"+
									"Student Name: "+userBean.getFirstName()+" "+userBean.getLastName()+"\n\n"+
									"Enrollment Number: "+userBean.getEnrollNo()+"\n\n"+
									"Outpass From Date: "+ob.getFromDate()+"\n\n"+
									"Outpass To Date: "+ob.getToDate()+"\n\n"+
									"Place of Travel: "+ob.getPlace()+"\n\n"+
									"Reason: "+ob.getReason()+"\n\n"+
									"Please click on the link to approve \n\n\n"+
									"http://localhost:8080/hms/approve.parent?outpassId="+ob.getOutpassId();
				
				SendEmail.send(emailSubject, emailBody, userBean.getParentEmail(), userBean.getStudentEmail(), "madd.vivek@gmail.com", "chahal_monika@yahoo.co.in");
			}
			
			view.addStaticAttribute("success", true);
			view.addStaticAttribute("message", "Outpass Applied Successfully");
		} catch (Exception e) {
			view.addStaticAttribute("success", false);
			view.addStaticAttribute("message", "something wrong");
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "approve.parent", method = RequestMethod.GET)
	public String parentApproval(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Parent Approve outpass");
		try {
			HttpSession session = request.getSession();
			
			Integer outpassId = new Integer(request.getParameter("outpassId"));
			
			System.out.println(outpassId);
			
			OutpassBean outpassBean = hmsService.getOutpass(outpassId);
			outpassBean.setOutpassStatus("PA");
			// Update the TOUTPASS table
			hmsService.updateOutpass(outpassBean);
			
			UserBean userBean = hmsService.loginCheck(outpassBean.getEnrollNo());
			
			String emailSubject = "Amity Request For OutPass: "+ userBean.getFirstName()+" : "+userBean.getEnrollNo();
			String emailBody = "Dear Warden, \n\n\n"+
								"Outpass Request: \n\n"+
								"Student Name: "+userBean.getFirstName()+" "+userBean.getLastName()+"\n\n"+
								"Enrollment Number: "+userBean.getEnrollNo()+"\n\n"+
								"Outpass From Date: "+outpassBean.getFromDate()+"\n\n"+
								"Outpass To Date: "+outpassBean.getToDate()+"\n\n"+
								"Place of Travel: "+outpassBean.getPlace()+"\n\n"+
								"Reason: "+outpassBean.getReason()+"\n\n\n"+
								"Please login to the portal and approve the same.\n\n\n Outpass ID: "+outpassBean.getOutpassId();
			
			SendEmail.send(emailSubject, emailBody, "chahal_monika@yahoo.co.in", userBean.getStudentEmail());
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "outpassApplied";
	}
	
	
	@RequestMapping(value = "getUserDetails")
	public MappingJacksonJsonView getUserDetails(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Apply Outpass!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			HttpSession session = request.getSession();
			if(session.isNew()) {
				logger.info("New Session");
			} else {
				logger.info("Old Session");
			}
			UserBean userBean = (UserBean) session.getAttribute("user");
			List<UserBean> userList = new ArrayList<UserBean>();
			userList.add(userBean);
			
			view.addStaticAttribute("success", true);
			view.addStaticAttribute("user", userBean);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return view;
	}
	
	
	@RequestMapping(value = "getMyOutpass")
	public MappingJacksonJsonView getMyOutpass(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Get Outpass!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			HttpSession session = request.getSession();
			UserBean userBean = (UserBean) session.getAttribute("user");
			List<OutpassBean> outpassList = hmsService.getMyOutpass(userBean.getEnrollNo());
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
}
