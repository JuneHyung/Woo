package com.fridge.model.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Fridge;


public interface FridgeService {

	void fridgeinfoinsert(Fridge fridge);

}