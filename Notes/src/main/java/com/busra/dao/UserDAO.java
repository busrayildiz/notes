package com.busra.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.busra.entity.Note;
import com.busra.entity.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	//CRUD

	public Long insert(User user) {

		return (Long) sessionFactory.getCurrentSession().save(user);			 
	}

	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	//	
	//	
	//	public void delete(Note note) {
	//		sessionFactory.getCurrentSession().delete(note);
	//	}
	//	
	public User getUserByUnameAndPass(String username , String pass ){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username AND pass=:pass AND active=:active")
				.setString("username", username)
				.setString("pass", pass)
				.setBoolean("active", true);
		User user = null;

		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;

	}

	public User getUserByUname(String username ){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:userame")
				.setString("username", username);

		return (User) query.getSingleResult();
	}

	public User getUserByKey(String key){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE keyReg=:key")
				.setString("key", key);

		User user = null;

		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;


	}

}
