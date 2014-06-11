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
import com.amity.hms.service.HmsService;

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
}
