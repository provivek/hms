package com.amity.hms.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amity.hms.beans.TestBean;
import com.amity.hms.service.HmsService;

@Controller
public class RegisterationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterationController.class);
	
	@Autowired
	private HmsService hmsService;
	
	@RequestMapping(value = "reg.user", method = RequestMethod.POST)
	public void registerUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Welcome home! The client locale is {}.");
		
		System.out.println("Test DB connection");
		List<TestBean> tt = hmsService.getTest();
		
		
		//return "home";
	}
}
