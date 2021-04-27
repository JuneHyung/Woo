package com.fridge.Service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.Dto.Fridge;


public interface FridgeService {

	void fridgeinfoinsert(Fridge fridge);

}
