package com.fridge.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Ingredientsdetail;
import com.fridge.model.Recipe;
import com.fridge.model.service.DataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "DataController v0.1")
@RestController
@RequestMapping("/data")
public class DataController {
	   private static final String SUCCESS = "success";
	   private static final String FAIL = "fail";
	   
	   @Autowired
	   private DataService dataService;
	   
	   @Operation(summary = "레시피 추가", description = "레시피 추가")
	   @PostMapping("/createRecipe")
	   public ResponseEntity<Map<String, Object>> createRecipe(
			   @RequestBody Recipe recipe){
		   Map<String, Object> resultMap = new HashMap<String, Object>();
		   HttpStatus status = null;
		   try {
			   dataService.creteRecipe(recipe);
			   resultMap.put("messgae", SUCCESS);
			   status = HttpStatus.ACCEPTED;
		   } catch(Exception e) {
			   resultMap.put("message", FAIL);
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
		   }
		   return new ResponseEntity<Map<String, Object>>(resultMap, status);
	   }
	   
	   @Operation (summary = "Ingredientsdetail 추가", description = "Ingredientsdetail 추가")
	   @PostMapping("/createIngredientsdetail")
	   public ResponseEntity<Map<String, Object>> createIngredientsdetail(
			   @RequestBody Ingredientsdetail ingredientsdetail){
		   Map<String, Object> resultMap = new HashMap<String, Object>();
		   HttpStatus status = null;
		   try {
			   dataService.createIngredientsdetail(ingredientsdetail);
			   resultMap.put("message", SUCCESS);
			   status = HttpStatus.ACCEPTED;
		   }catch(Exception e) {
			   resultMap.put("message", FAIL);
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
		   }
		   return new ResponseEntity<Map<String, Object>>(resultMap, status);
	   }
	   
	   
}
