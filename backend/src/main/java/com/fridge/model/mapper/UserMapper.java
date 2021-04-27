package com.fridge.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.fridge.model.UserDto;

public interface UserMapper {
	public UserDto login(UserDto userDto) throws SQLException;

	public void join(Map<String, Object> param) throws SQLException;
}
