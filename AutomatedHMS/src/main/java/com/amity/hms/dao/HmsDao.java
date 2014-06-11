package com.amity.hms.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.amity.hms.beans.OutpassBean;
import com.amity.hms.beans.TestBean;


@Repository
public class HmsDao {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TestBean> getTest() {
		DetachedCriteria criteria = DetachedCriteria.forClass(TestBean.class);
		return hibernateTemplate.findByCriteria(criteria);
		//return hibernateTemplate.findByCriteria(criteria, start, limit);
	}
	
	@SuppressWarnings("unchecked")
	public List<OutpassBean> getOutpass(String outpassStatus) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OutpassBean.class);
		criteria.add(Restrictions.eq("outpassStatus",outpassStatus));
		return hibernateTemplate.findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<OutpassBean> getMyOutpass(String userId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OutpassBean.class);
		criteria.add(Restrictions.eq("enrollNo",userId));
		return hibernateTemplate.findByCriteria(criteria);
	}
	
	public void save(Object object) {
		hibernateTemplate.save(object);
	}
	
	public Object saveOutpass(Object object) {
		return hibernateTemplate.save(object);
	}
	
	public void delete(Object obj) {
		hibernateTemplate.delete(obj);
	}
	
	public <T> Object getUser(Class<T> cls, String id) {
		Object result = hibernateTemplate.get(cls, id);
		return result;
	}
	
	public <T> Object getOutpass(Class<T> cls, Number id) {
		Object result = hibernateTemplate.get(cls, id);
		return result;
	}
	
	public void saveOrUpdate(Object obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}
}
