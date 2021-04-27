package com.fridge.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Fridge;
import com.fridge.model.Main;
import com.fridge.model.Recipe;
import com.fridge.model.service.RecipesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
	@Autowired
	private RecipesService recipesService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Operation(summary = "레시피 정보 입력", description = "레시피를 입력한다")
	@PostMapping
	public ResponseEntity<Map<String, Object>> recipeinsert(@RequestBody Recipe recipe){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			recipesService.recipeinsert(recipe);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@Operation(summary = "레시피 정보 조회", description = "레시피를 조회한다")
	@GetMapping("/detail/{recipe_id}")
	public ResponseEntity<Map<String, Object>> recipedetail(@PathVariable int recipe_id){
//		resultMap.put("recipe", re);
		
		return null;
	}

	@Operation(summary = "메인 재료 입력", description = "재를 입력한다")
	@PostMapping("/main")
	public ResponseEntity<Map<String, Object>> maininsert(@RequestBody Main main){
		System.out.println(main);
		return null;
	}
	
	@Operation(summary = "테스트 입력", description = "테스트 입력한다")
	@GetMapping("/testmain/{id}")
	public ResponseEntity<Map<String, Object>> tesetinsert(@PathVariable int id){
		System.out.println(id);
		return null;
	}

	
}
