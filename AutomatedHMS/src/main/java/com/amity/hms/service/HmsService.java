package com.amity.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amity.hms.beans.AuthBean;
import com.amity.hms.beans.OutpassBean;
import com.amity.hms.beans.TestBean;
import com.amity.hms.beans.UserBean;
import com.amity.hms.dao.HmsDao;

public class HmsService {
	
	@Autowired
	private HmsDao hmsDao;
	
	public List<TestBean> getTest() {
		return hmsDao.getTest();
	}
	
	public List<OutpassBean> getOutpass(String outpassStatus) {
		return hmsDao.getOutpass(outpassStatus);
	}
	
	public List<OutpassBean> getMyOutpass(String userId) {
		return hmsDao.getMyOutpass(userId);
	}
	
	public void registerUser(UserBean userBean) {
		hmsDao.save(userBean);
	}
	
	public void createAuthDetails(AuthBean authBean){
		hmsDao.save(authBean);
	}
	
	public OutpassBean createOutpass(OutpassBean outpassBean) {
		Object o = hmsDao.saveOutpass(outpassBean);
		System.out.println(o);
		return outpassBean;
	}
	
	public UserBean loginCheck(String userName){
		UserBean ub = (UserBean) hmsDao.getUser(UserBean .class, userName);
		return ub;
	}
	
	public AuthBean getWarden(String userName){
		AuthBean authBean = (AuthBean) hmsDao.getUser(AuthBean.class, userName);
		return authBean;
	}
	
	public void deleteOutpass(OutpassBean outpassBean) {
		hmsDao.delete(outpassBean);
	}
	
	public OutpassBean getOutpass(Integer outpassId) {
		OutpassBean outpassBean = (OutpassBean) hmsDao.getOutpass(OutpassBean .class, outpassId);
		return outpassBean;
	}
	
	public void updateOutpass(OutpassBean outpassBean) {
		hmsDao.saveOrUpdate(outpassBean);
	}
}
