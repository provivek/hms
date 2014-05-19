package com.amity.hms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.amity.hms.beans.AuthBean;
import com.amity.hms.beans.UserBean;
import com.amity.hms.service.HmsService;

@Controller
public class RegisterationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterationController.class);
	
	@Autowired
	private HmsService hmsService;
	
	@RequestMapping(value = "reg.user", method = RequestMethod.POST)
	public MappingJacksonJsonView registerUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserBean userBean) {
		logger.info("Register User!!");
		MappingJacksonJsonView view = null;
		try {
			view = new MappingJacksonJsonView();
			hmsService.registerUser(userBean);
			
			AuthBean authBean = new AuthBean();
			authBean.setUserId(BeanUtils.getProperty(userBean, "enrollNo"));
			authBean.setPassword(BeanUtils.getProperty(userBean, "pass"));
			authBean.setEmailId(BeanUtils.getProperty(userBean, "studentEmail"));
			authBean.setUserType("S");
			hmsService.createAuthDetails(authBean);
			view.addStaticAttribute("success", true);
			view.addStaticAttribute("redirectURL", "studentHome");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
}
