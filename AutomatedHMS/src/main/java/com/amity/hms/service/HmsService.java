package com.amity.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amity.hms.beans.TestBean;
import com.amity.hms.dao.HmsDao;

public class HmsService {
	
	@Autowired
	private HmsDao hmsDao;
	
	public List<TestBean> getTest() {
		return hmsDao.getTest();
	}

}
