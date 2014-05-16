package com.amity.hms.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.amity.hms.beans.TestBean;


@Repository
public class HmsDao {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	public List<TestBean> getTest() {
		DetachedCriteria criteria = DetachedCriteria.forClass(TestBean.class);
		return hibernateTemplate.findByCriteria(criteria);
		//return hibernateTemplate.findByCriteria(criteria, start, limit);
	}
}
