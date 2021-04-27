package com.fridge.model.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Fridge;


public interface FridgeService {

	void create(Fridge fridge) throws Exception;

	Fridge[] fridgeList(int id) throws Exception;

}
