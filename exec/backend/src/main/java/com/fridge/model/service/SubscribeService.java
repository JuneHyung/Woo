package com.fridge.model.service;

import java.security.Principal;
import java.util.List;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.dto.UserDto;

public interface SubscribeService {

	void insertScribe(Principal userId, int subscribeId) throws WrongFormException;

	List<UserDto> getScribe(Principal userId);

	void deleteScribe(Principal userId, int subscribeId) throws WrongFormException;


}
