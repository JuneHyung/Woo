package com.fridge.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;
import com.fridge.model.dto.FridgeDto;
import com.fridge.model.dto.IngredientsDto;
import com.fridge.model.service.FridgeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "fridgeController v0.1")
@RestController
@RequestMapping("/fridge")
public class FridgeController {
	private static final Logger logger = LoggerFactory.getLogger(FridgeController.class);

	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private FridgeService fridgeService;

	@Operation(summary = "냉장고 등록", description = "냉장고 정보를 입력하고 등록한다.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> create(@Parameter(name = "로그인한 유저") Principal user,
			@RequestBody @Parameter(name = "냉장고 등록에 필요한 정보", required = true) FridgeDto fridgeDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			fridgeService.create(user, fridgeDto);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "재료 추가", description = "냉장고에 재료 추가", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PostMapping("/addIngredients")
	public ResponseEntity<Map<String, Object>> addIngredients(
			@Parameter(name = "추가할 재료 정보 및 냉장고 아이디") @RequestBody IngredientsDto ingredientsDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			fridgeService.addIngredients(ingredientsDto);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "내 냉장고 목록 보기", description = "내가 등록한 냉장고의 정보를 가져온다.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> fridgeList(@Parameter(name = "로그인 유저") Principal user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Fridge[] fridgeList = fridgeService.fridgeList(Integer.parseInt(user.getName()));

			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("fridgeList", fridgeList);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "전체 재료 조회", description = "카테고리와 무관하게 전체 조회", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/ingredientsDetailList")
	public ResponseEntity<Map<String, Object>> ingredientsDetailList() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<Ingredientsdetail> ingredients = fridgeService.ingredientsDetailList();

			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("ingredients", ingredients);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "냉장고 포함 재료", description = "냉장고 번호를 통해 냉장고에 포함된 재료", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/ingredients/{fridge_id}")
	public ResponseEntity<Map<String, Object>> ingrediantsList(@PathVariable("fridge_id") int fridgeId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Ingredients[] ingredients = fridgeService.ingrediantsList(fridgeId);

			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("ingredients", ingredients);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "냉장고 디테일", description = "냉장고 번호를 통해 냉장고 디테일 정보", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/detail/{fridge_id}")
	public ResponseEntity<Map<String, Object>> fridgeDetail(@PathVariable("fridge_id") int fridgeId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Optional<Fridge> fridge = fridgeService.fridgeDetail(fridgeId);

			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("fridge", fridge);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "재료 카테고리 목록", description = "재료 추가를 위한 목록  제공", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/categoryList")
	public ResponseEntity<Map<String, Object>> categoryList() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			String[] category = fridgeService.categoryList();

			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("category", category);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "카테고리에 따른 재료", description = "카테고리별 재료 리스트 제공", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/categoryByingredients/{category}")
	public ResponseEntity<Map<String, Object>> categoryByingredientsList(@PathVariable("category") String category) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Ingredientsdetail[] ingredients = fridgeService.categoryByingredientsList(category);
			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("ingredients", ingredients);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "냉장고 삭제", description = "등록한 냉장고 삭제.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@DeleteMapping("/list/{fridgeId}")
	public ResponseEntity<Map<String, Object>> fridgeDel(@Parameter(name = "로그인 유저") Principal user,
			@PathVariable("fridgeId") int fridgeId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			fridgeService.fridgeDel(user, fridgeId);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "재료 제거", description = "냉장고 재료 제거", security = { @SecurityRequirement(name = "X-AUTH-TOKEN") })
	@DeleteMapping("/delIngredients/{ingredientsId}")
	public ResponseEntity<Map<String, Object>> delIngredients(@PathVariable("ingredientsId") int ingredientsId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			fridgeService.delIngredients(ingredientsId);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "재료 이동", description = "냉장고 내부에서 재료 이동", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PutMapping("/moveIngredients")
	public ResponseEntity<Map<String, Object>> moveIngredients(
			@Parameter(name = "이동할 위치를 포함한 Ingredients") @RequestBody IngredientsDto ingredientsDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			fridgeService.moveIngredients(ingredientsDto);
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