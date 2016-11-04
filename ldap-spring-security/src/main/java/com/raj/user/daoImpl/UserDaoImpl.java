package com.raj.user.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raj.beans.UserBean;
import com.raj.user.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Override
	public String saveOrUpdateUser(UserBean user) {
		logger.info("saveOrUpdateUser daoImpl");
		String status = "0";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if(null == user.getId()){
				session.save(user);
				tx.commit();
				status = "1";
			}else{
				session.update(user);
				tx.commit();
				status = "1";
			}
		} catch (Exception e) {
			logger.error("Exception: "+e.getMessage());
		} finally {
			if(session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UserBean> getAllUser() {
		logger.info("getAllUser daoImpl");
		List<UserBean> list = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("From UserBean");
			list = query.getResultList();
			logger.info("Total User: "+list.size());
		} catch(Exception e){
			logger.error("Exception: "+e.getMessage());
		} finally {
			if(session.isOpen()){
				session.close();
			}
		}
		return list;
	}

	@Override
	public UserBean getUserById(Integer id) {
		logger.info("getUserById daoImpl");
		UserBean bean = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			bean = session.get(UserBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: "+e.getMessage());
		} finally {
			if(session.isOpen()){
				session.close();
			}
		}
		return bean;
	}

	@Override
	public String deleteUser(Integer id) {
		logger.info("deleteUser daoImpl");
		String status = "0";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			UserBean bean = session.load(UserBean.class, id);
			if(null != bean){
				session.delete(bean);
				tx.commit();
				status = "1";
			}
		} catch (Exception e) {
			logger.error("Exception: "+e.getMessage());
		} finally {
			if(session.isOpen()){
				session.close();
			}
		}
		return status;
	}

}
