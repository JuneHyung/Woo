package com.fridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.Service.FridgeService;
import com.fridge.model.Fridge;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/fridge")
public class FridgeController {
	
	@Autowired
	FridgeService fridgeService;
	
	
	@PostMapping("/fridgeinfo")
	@Operation(summary = "냉장고 정보 입력", description = "냉장고 정보를 받아 저장한다.")
	private String fridgeinfoinsert(@RequestBody Fridge fridge) {
		fridgeService.fridgeinfoinsert(fridge);
		return "success";
	}
}
