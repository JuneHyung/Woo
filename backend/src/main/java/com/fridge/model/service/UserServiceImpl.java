package com.fridge.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.User;
import com.fridge.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userrepo;
	@Override
	public User login(User user) throws Exception {
		if(user.getEmail() == null || user.getPwd() == null)
			return null;
		
		return userrepo.findByEmailAndPwd(user.getEmail(), user.getPwd());
	}

	@Override
	public void join(User user) throws Exception {
		userrepo.save(user);
	}
	
}
