package com.fridge.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "레시비 목록 조회" ,description = "레시피 목록을 조회한다")
	@GetMapping
	public ResponseEntity<Map<String, Object>> recipelist(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Recipe> list = recipesService.recipelist();
			resultMap.put("recipelist", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "레시피 정보 조회", description = "레시피를 조회한다")
	@GetMapping("/detail/{recipe_id}")
	public ResponseEntity<Map<String, Object>> recipedetail(@PathVariable int recipe_id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			Optional<Recipe> re = recipesService.recipeselect(recipe_id);
			resultMap.put("recipe", re);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "특정재료를 메인으로 하는 레시피 조회",description = "특정 재료를 메인으로 하는 레시피를 조회한다")
	@GetMapping("/ingredients/{id}")
	public ResponseEntity<Map<String, Object>> ingredientrecipe(@PathVariable int id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Main> recipelist = recipesService.ingredientrecipes(id);
			resultMap.put("recipelist", recipelist);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", FAIL);
		}
		return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}
//	@Operation(summary = "메인 재료 입력", description = "재료를 입력한다")
//	@PostMapping("/main")
//	public ResponseEntity<Map<String, Object>> maininsert(@RequestBody Main main){
//		System.out.println(main);
//		return null;
//	}
//	
//	@Operation(summary = "테스트 입력", description = "테스트 입력한다")
//	@GetMapping("/testmain/{id}")
//	public ResponseEntity<Map<String, Object>> tesetinsert(@PathVariable int id){
//		System.out.println(id);
//		return null;
//	}
	
	@Operation(summary = "레시피 확인시 views 수 증가", description = "views 증가")
	@GetMapping("/views/{recipe_id}")
	public ResponseEntity<Map<String, Object>> upViews(@PathVariable("recipe_id") int recipe_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			recipesService.upViews(recipe_id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			resultMap.put("mewssage", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
