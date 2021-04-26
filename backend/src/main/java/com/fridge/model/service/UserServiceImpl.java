package com.fridge.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.UserDto;
import com.fridge.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getEmail() == null || userDto.getPwd() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}

	@Override
	public void join(Map<String, Object> param) throws Exception {
		sqlSession.getMapper(UserMapper.class).join(param);
	}
	
}
