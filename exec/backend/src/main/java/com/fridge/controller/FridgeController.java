package com.fridge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;
import com.fridge.model.service.FridgeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "fridgeController v0.1")
@RestController
@RequestMapping("/fridge")
public class FridgeController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private FridgeService fridgeService;

	@Operation(summary = "냉장고 등록", description = "냉장고 정보를 입력하고 등록한다.")
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> create(
			@RequestBody @Parameter(name = "냉장고 등록에 필요한 정보", required = true) Fridge fridge) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			fridgeService.create(fridge);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "내 냉장고 목록 보기", description = "내가 등록한 냉장고의 정보를 가져온다.")
	@GetMapping("/list/{user_id}")
	public ResponseEntity<Map<String, Object>> fridgeList(@PathVariable("user_id") int user_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			Fridge[] fridgeList = fridgeService.fridgeList(user_id);
			resultMap.put("message", SUCCESS);
			resultMap.put("fridgeList", fridgeList);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "냉장고 삭제", description = "등록한 냉장고 삭제.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@DeleteMapping("/list/{fridge_id}")
	public ResponseEntity<Map<String, Object>> fridgeDel(@PathVariable("fridge_id") int fridge_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			fridgeService.fridgeDel(fridge_id);
			resultMap.put("meesage", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "냉장고 디테일", description = "냉장고 번호를 통해 냉장고 디테일 정보")
	@GetMapping("/detail/{fridge_id}")
	public ResponseEntity<Map<String, Object>> fridgeDetail(@PathVariable("fridge_id") int fridge_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			Optional<Fridge> fridge = fridgeService.fridgeDetail(fridge_id);
			resultMap.put("message", SUCCESS);
			resultMap.put("fridge", fridge);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "냉장고 포함 재료", description = "냉장고 번호를 통해 냉장고에 포함된 재료")
	@GetMapping("/ingredients/{fridge_id}")
	public ResponseEntity<Map<String, Object>> ingrediantsList(@PathVariable("fridge_id") int fridge_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			Ingredients[] ingredients = fridgeService.ingrediantsList(fridge_id);
			resultMap.put("message", SUCCESS);
			resultMap.put("ingredients", ingredients);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "재료 카테고리 목록", description = "재료 추가를 위한 목록  제공")
	@GetMapping("/categoryList")
	public ResponseEntity<Map<String, Object>> categoryList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			String[] category = fridgeService.categoryList();
			resultMap.put("message", SUCCESS);
			resultMap.put("category", category);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "카테고리에 따른 재료", description = "카테고리별 재료 리스트 제공")
	@GetMapping("/categoryByingredients/{category}")
	public ResponseEntity<Map<String, Object>> categoryByingredientsList(@PathVariable("category") String category) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			Ingredientsdetail[] ingredients = fridgeService.categoryByingredientsList(category);
			resultMap.put("message", SUCCESS);
			resultMap.put("ingredients", ingredients);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "전체 재료 조회", description = "카테고리와 무관하게 전체 조회")
	@GetMapping("/ingredientsDetailList")
	public ResponseEntity<Map<String, Object>> ingredientsDetailList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Ingredientsdetail> ingredients = fridgeService.ingredientsDetailList();
			resultMap.put("message", SUCCESS);
			resultMap.put("ingredients", ingredients);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "재료 추가", description = "냉장고에 재료 추가")
	@PostMapping("/addIngredients")
	public ResponseEntity<Map<String, Object>> addIngredients(@RequestBody Ingredients ingredients) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			fridgeService.addIngredients(ingredients);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "재료 제거", description = "냉장고 재료 제거")
	@DeleteMapping("delIngredients/{ingredients_id}")
	public ResponseEntity<Map<String, Object>> delIngredients(@PathVariable("ingredients_id") int ingredients_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			fridgeService.delIngredients(ingredients_id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}