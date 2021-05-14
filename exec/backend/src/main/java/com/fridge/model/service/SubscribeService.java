package com.fridge.model.service;

import java.security.Principal;
import java.util.List;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.dto.UserDto;

public interface SubscribeService {

	void insertSubscribe(Principal userId, int subscribeId) throws WrongFormException;

	List<UserDto> getSubscribe(Principal userId);

	void deleteSubscribe(Principal userId, int subscribeId) throws WrongFormException;

}
