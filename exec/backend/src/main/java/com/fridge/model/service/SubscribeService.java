package com.fridge.model.service;

import java.util.List;

import com.fridge.model.Subscribe;
import com.fridge.model.User;

public interface SubscribeService {

	void insertscribe(Subscribe subscribe) throws Exception;

	List<User> getscribe(int id) throws Exception;

	void deletescribe(int id) throws Exception;


}
