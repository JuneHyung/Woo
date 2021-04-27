package com.fridge.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Fridge;
import com.fridge.model.repository.FridgeRepository;

@Service
public class FridgeServiceImpl implements FridgeService{
	@Autowired
	private FridgeRepository fridgeRepository;

	@Override
	public void fridgeinfoinsert(Fridge fridge) {
		// TODO Auto-generated method stub
		fridgeRepository.save(fridge);
	}
	
	
}
