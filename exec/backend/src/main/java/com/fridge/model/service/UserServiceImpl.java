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
	public void checkEmail(String email) throws Exception {
		if(userRepository.findByEmail(email).isPresent())
			throw new Exception("이미 있는 가입되어 있는 이메일입니다.");
	}
	
	@Override
	public void checkNick(String nick) throws Exception {
		if(userRepository.findByNick(nick).isPresent())
			throw new Exception("사용 중인 닉네임입니다.");
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
