package com.fridge.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.Dao.FridgeRepository;
import com.fridge.Dto.Fridge;

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
