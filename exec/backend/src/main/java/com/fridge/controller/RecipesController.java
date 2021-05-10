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

	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Operation(summary = "레시피 정보 입력", description = "레시피를 입력한다")
	@PostMapping
	public ResponseEntity<Map<String, Object>> recipeinsert(@RequestBody Recipe recipe) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			recipesService.recipeInsert(recipe);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "레시비 목록 조회", description = "레시피 목록을 조회한다")
	@GetMapping("/{page}/{size}")
	public ResponseEntity<Map<String, Object>> recipelist(@PathVariable("page") int page,
			@PathVariable("size") int size) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<Recipe> list = recipesService.recipeList(page, size);

			resultMap.put("recipelist", list);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "레시피 정보 조회", description = "레시피를 조회한다")
	@GetMapping("/detail/{recipeId}")
	public ResponseEntity<Map<String, Object>> recipedetail(@PathVariable("recipeId") int recipeId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Optional<Recipe> re = recipesService.recipeSelect(recipeId);
			resultMap.put("recipe", re);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "특정재료를 메인으로 하는 레시피 조회", description = "특정 재료를 메인으로 하는 레시피를 조회한다")
	@GetMapping("/ingredients/{id}/{page}/{size}")
	public ResponseEntity<Map<String, Object>> ingredientrecipe(@PathVariable("id") int id, @PathVariable("page") int page,
			@PathVariable("size") int size) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<Recipe> recipelist = recipesService.ingredientRecipes(id, page, size);

			resultMap.put("recipelist", recipelist);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "레시피 확인시 views 수 증가", description = "views 증가")
	@GetMapping("/views/{recipeId}")
	public ResponseEntity<Map<String, Object>> upViews(@PathVariable("recipeId") int recipeId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			recipesService.upViews(recipeId);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}
	

}
