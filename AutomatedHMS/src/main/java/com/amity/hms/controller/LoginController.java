package com.amity.hms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amity.hms.beans.AuthBean;
import com.amity.hms.service.HmsService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterationController.class);
	
	@Autowired
	private HmsService hmsService;
	
	@RequestMapping(value = "login.check", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute AuthBean authBean) {
		logger.info("Register User!!");
		ModelAndView view = null;
		try {
			view = new ModelAndView("studentHome");
			
			view.addObject("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
}
