package com.fridge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Post Controller v0.1")
@RestController
@RequestMapping("/post")
public class PostController {
	public static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PostService postService;

	@Operation(summary = "사용자 레시피 등록", description = "레시피 정보를 등록한다.")
	@PostMapping(path = "/upload", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<Map<String, Object>> upload(
			@Parameter(description = "레시피 타이틀", required = true) @RequestParam(name = "title") String title,
			@Parameter(description = "레시피 이미지들", required = true) @RequestParam("images") List<MultipartFile> images,
			@Parameter(description = "사용자 pk", required = true) @RequestParam("id") int id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			postService.upload(title, images, id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			logger.error(e.toString());
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
