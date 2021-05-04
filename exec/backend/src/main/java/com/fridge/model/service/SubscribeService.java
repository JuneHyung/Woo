package com.fridge.model.service;

import java.util.List;

import com.fridge.model.Subscribe;
import com.fridge.model.User;

public interface SubscribeService {

	void insertscribe(Subscribe subscribe);

	List<User> getscribe(int id);

	void deletescribe(int id);


}
