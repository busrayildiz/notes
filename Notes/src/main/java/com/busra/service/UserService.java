package com.busra.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busra.dao.UserDAO;
import com.busra.entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserDAO userDAO;
	
	//CRUD
	
	public Long insert(User user) {
		
		String uuid = UUID.randomUUID().toString();
		user.setKeyReg(uuid);
		if(userDAO.insert(user)<0) {
			mailService.registerMail(user.getEmail(), user.getKeyReg());
		}			 
		return 1l ;
	}
	
	public void update(User user) {
		userDAO.update(user);
	}

	public User getFindByUnameAndPass(String username , String pass ){
		return userDAO.getNoteByUnameAndPass(username, pass);
		
	}
	
	public User getFindByUname(String username ){
		
		return userDAO.getNoteByUname(username);
	}
	
public boolean getFindByKey(String key ){
		User user = userDAO.getNoteByKey(key);
		if( user!=null) {
			user.setActive(true);
			update(user);
			return true;
		}else
		    return false;
	}
	
}