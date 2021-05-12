package com.fridge.model.service;

import java.security.Principal;

import org.springframework.dao.DuplicateKeyException;

import com.fridge.common.error.LoginErrorException;
import com.fridge.common.error.WrongFormException;
import com.fridge.common.error.WrongPasswordException;
import com.fridge.model.User;
import com.fridge.model.dto.UserDto;

public interface UserService {
	public Integer login(User user) throws LoginErrorException;

	public void join(User user) throws WrongFormException;

	public void modify(Principal loginId, User user) throws WrongPasswordException;

	public void delete(int id);

	public UserDto getUserInfo(String id) throws WrongFormException;

	public void checkEmail(String email) throws DuplicateKeyException;

	public void checkNick(String nick) throws DuplicateKeyException;

	public void changPwd(Principal userId, String legacyPwd, String newPwd) throws WrongPasswordException;
}
