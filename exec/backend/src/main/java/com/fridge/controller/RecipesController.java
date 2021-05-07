package com.fridge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Recipe;
import com.fridge.model.service.RecipesService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
	private static final Logger logger = LoggerFactory.getLogger(RecipesController.class);

	@Autowired
	private RecipesService recipesService;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Operation(summary = "레시피 정보 입력", description = "레시피를 입력한다")
	@PostMapping
	public ResponseEntity<Map<String, Object>> recipeinsert(@RequestBody Recipe recipe) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			recipesService.recipeInsert(recipe);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "레시비 목록 조회", description = "레시피 목록을 조회한다")
	@GetMapping("/{page}/{size}")
	public ResponseEntity<Map<String, Object>> recipelist(@PathVariable("page") int page,
			@PathVariable("size") int size) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Recipe> list = recipesService.recipeList(page, size);

			resultMap.put("recipelist", list);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "레시피 정보 조회", description = "레시피를 조회한다")
	@GetMapping("/detail/{recipe_id}")
	public ResponseEntity<Map<String, Object>> recipedetail(@PathVariable int recipe_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			Optional<Recipe> re = recipesService.recipeSelect(recipe_id);
			resultMap.put("recipe", re);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "특정재료를 메인으로 하는 레시피 조회", description = "특정 재료를 메인으로 하는 레시피를 조회한다")
	@GetMapping("/ingredients/{id}/{page}/{size}")
	public ResponseEntity<Map<String, Object>> ingredientrecipe(@PathVariable int id, @PathVariable int page,
			@PathVariable int size) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Recipe> recipelist = recipesService.ingredientRecipes(id, page, size);

			resultMap.put("recipelist", recipelist);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

}
