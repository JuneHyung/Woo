package com.fridge.model.service;

import java.util.Map;

import com.fridge.model.UserDto;

public interface UserService  {
	public UserDto login(UserDto userDto) throws Exception;
	
	public void join(Map<String, Object> param) throws Exception;
}
