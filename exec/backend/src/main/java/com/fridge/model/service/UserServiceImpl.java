package com.fridge.model.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fridge.model.CustomUserDetail;
import com.fridge.model.User;
import com.fridge.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public User login(User user) throws Exception {
		if(user.getEmail() == null || user.getPwd() == null)
			return null;
		
		return userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
	}

	@Override
	public void join(User user) throws Exception {
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(Integer.parseInt(username)).get();
		if (user == null)
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		UserDetails userDetails = new CustomUserDetail(user);
		
		return userDetails;
	}

	@Override
	public void modify(Principal loginId, User user) throws Exception {
		User u = new User(Integer.parseInt(loginId.getName()), user.getEmail(), user.getPwd(), user.getNick());
		
		userRepository.save(u);
	}

	@Override
	public void delete(int id) throws Exception {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserInfo(String id) throws Exception {
		return userRepository.findById(Integer.parseInt(id)).get();
	}
	
}
