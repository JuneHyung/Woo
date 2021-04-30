package com.fridge.model.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fridge.model.User;
import com.fridge.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) userrepo.findById(Integer.parseInt(username))
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
	}

	@Override
	public void modify(User user) throws Exception {
		User u = userrepo.getOne(user.getId());
		System.out.println(u.toString());
		u.setEmail(user.getEmail());
		u.setPwd(user.getPwd());
		u.setNick(user.getNick());
		userrepo.save(u);
	}
	
}
