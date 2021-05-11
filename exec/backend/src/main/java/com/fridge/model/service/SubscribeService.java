package com.fridge.model.service;

import java.security.Principal;
import java.util.List;

import com.fridge.model.dto.UserDto;

public interface SubscribeService {

	void insertScribe(Principal userId, int subscribeId) throws Exception;

	List<UserDto> getScribe(Principal userId) throws Exception;

	void deleteScribe(Principal userId, int subscribeId) throws Exception;


}
