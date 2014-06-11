package com.amity.hms.controller;

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

import com.amity.hms.beans.AuthBean;
import com.amity.hms.beans.UserBean;
import com.amity.hms.service.HmsService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterationController.class);
	
	@Autowired
	private HmsService hmsService;
	
	@RequestMapping(value = "validate.login", method = RequestMethod.POST)
	public MappingJacksonJsonView loginUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AuthBean authBean) {
		logger.info("Register User!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			// check username
			AuthBean authBeanDao = hmsService.getWarden(authBean.getUserId());
			
			if(authBeanDao.getUserType().equals("S")){
				HttpSession session = request.getSession();
				UserBean ub = hmsService.loginCheck(authBean.getUserId());
				if(ub == null) {
					view.addStaticAttribute("success", false);
					view.addStaticAttribute("message", "User name not present");
				} else if (!authBean.getPassword().equals(ub.getPass())) {
					view.addStaticAttribute("success", false);
					view.addStaticAttribute("message", "Password is incorrect");
				} else {
					if(session.isNew()) {
						logger.info("New Session");
					} else {
						logger.info("Old Session");
						session.setAttribute("user", ub);
					}
					view.addStaticAttribute("success", true);
					view.addStaticAttribute("message", "authenticated");
					view.addStaticAttribute("redirectURL", "/studentHome");
				}
			} else if (authBeanDao.getUserType().equals("W")) {
				if(authBeanDao == null) {
					view.addStaticAttribute("success", false);
					view.addStaticAttribute("message", "User name not present");
				} else if (!authBean.getPassword().equals(authBeanDao.getPassword())) {
					view.addStaticAttribute("success", false);
					view.addStaticAttribute("message", "Password is incorrect");
				} else {
					
					view.addStaticAttribute("success", true);
					view.addStaticAttribute("message", "authenticated");
					view.addStaticAttribute("redirectURL", "/wardenHome");
				}
			}
			
			
		} catch (Exception e) {
			view.addStaticAttribute("success", false);
			view.addStaticAttribute("message", "something wrong");
			e.printStackTrace();
		}
		return view;
	}
}
