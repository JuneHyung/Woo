package com.fridge.model.service;

import java.security.Principal;

import org.springframework.dao.DuplicateKeyException;

import com.fridge.common.error.LoginErrorException;
import com.fridge.common.error.WrongFormException;
import com.fridge.common.error.WrongPasswordException;
import com.fridge.model.dto.UserDto;
import com.fridge.model.dto.UserInfoDto;

public interface UserService {
	public Integer login(UserInfoDto loginUserInfoDto) throws LoginErrorException;

	public void join(UserInfoDto signUpUserInfoDto) throws WrongFormException;

	public void modify(Principal loginId, UserInfoDto modifyUserInfoDto) throws WrongPasswordException;

	public void delete(int id);

	public UserDto getUserInfo(String id) throws WrongFormException;

	public void checkEmail(String email) throws DuplicateKeyException;

	public void checkNick(String nick) throws DuplicateKeyException;

	public void changPwd(Principal userId, String legacyPwd, String newPwd) throws WrongPasswordException;
}
