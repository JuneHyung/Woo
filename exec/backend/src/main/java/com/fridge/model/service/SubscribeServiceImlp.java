package com.fridge.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Subscribe;
import com.fridge.model.User;
import com.fridge.model.repository.SubscribeRepository;
import com.fridge.model.repository.UserRepository;
@Service
public class SubscribeServiceImlp implements SubscribeService{
	@Autowired
	SubscribeRepository subscribeRepository;
	@Autowired
	UserRepository userrepository;
	@Override
	public void insertscribe(Subscribe subscribe) throws Exception {
		// TODO Auto-generated method stub
		subscribeRepository.save(subscribe);
	}
	@Override
	public List<User> getscribe(int id) throws Exception{
		// TODO Auto-generated method stub
		return userrepository.findByIdIn(subscribeRepository.finduserid(id));
	}
	@Override
	public void deletescribe(int id) throws Exception{
		// TODO Auto-generated method stub
		subscribeRepository.deleteById(id);
	}

}
