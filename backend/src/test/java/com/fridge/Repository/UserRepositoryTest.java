package com.fridge.Repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fridge.BackendApplicationTests;
import com.fridge.Dao.UserRepository;
import com.fridge.Dto.User;

public class UserRepositoryTest extends BackendApplicationTests{
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void create() {
		User user = new User();
		user.setEmail("test1@gmail.com");
		user.setNick("jo23");
		user.setPwd("123");
		
		User newuser = userRepository.save(user);
		System.out.println(newuser);
	}
	
//	@Test
//	public void read() {
//		Optional<User> user = userRepository.findById(1);
//		
//		user.ifPresent(selectUser->{
//			System.out.println("user : " + selectUser.toString());
//		});
//	}
}
