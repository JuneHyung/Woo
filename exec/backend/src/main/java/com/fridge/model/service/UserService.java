package com.fridge.model.service;

import java.security.Principal;

import org.springframework.dao.DuplicateKeyException;

import com.fridge.model.User;

public interface UserService {
	public User login(User user) throws Exception;

	public void join(User user) throws Exception;

	public void modify(Principal loginId, User user) throws Exception;

	public void delete(int id) throws Exception;

	public User getUserInfo(String id) throws Exception;

	public void checkEmail(String email) throws DuplicateKeyException;

	public void checkNick(String nick) throws DuplicateKeyException;

	public void changPwd(Principal userId, String legacyPwd, String newPwd) throws Exception;
}
