package com.fridge.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmailAndPwd(String email,String pwd);

	Optional<User> findById(int id);

	
//	@Modifying
//	@Query("update User set email = :#{#user.email}, pwd = :#{#user.pwd}, nick = :#{#user.nick} where id = :#{#user.id}")
//	void updateById(User user);
	
}
