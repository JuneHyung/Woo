package com.fridge.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.dto.PostDto;
import com.fridge.model.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Post Controller v0.1")
@RestController
@RequestMapping("/post")
public class PostController {
	public static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PostService postService;

	@Operation(summary = "사용자 레시피 등록", description = "레시피 정보를 등록한다.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PostMapping(path = "/upload", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<Map<String, Object>> upload(@Parameter(description = "사용자 pk") Principal id,
			@Parameter(description = "레시피 타이틀", required = true) @RequestParam(name = "title") String title,
			@Parameter(description = "레시피 이미지들", required = true) @RequestParam("images") List<MultipartFile> images) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			postService.upload(title, images, id);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "자기글 확인", description = "자기가 쓴글을 확인한다", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping(path = "/mylist/{page}/{size}")
	public ResponseEntity<Map<String, Object>> getMyPosLlist(@Parameter(description = "로그인 유저 ID") Principal userId,
			@PathVariable("page") int page, @PathVariable("size") int size) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<PostDto> myPostList = postService.getMyPosLlist(page, size, Integer.parseInt(userId.getName()));

			resultMap.put("myPostList", myPostList);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "사용자 등록 레시피 목록", description = "사용자가 등록한 레시피 목폭을 최신 순으로 제공. image 한장만 우선 제공")
	@GetMapping(path = "/list/{page}/{size}")
	public ResponseEntity<Map<String, Object>> getPostList(@PathVariable("page") int page,
			@PathVariable("size") int size) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<PostDto> postList = postService.getPostList(page, size);

			resultMap.put("postList", postList);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "사용자 등록 레시피 상세정보", description = "선택한 사용자 등록 레시피 상세 정보를 제공한다.")
	@GetMapping(path = "/detail/{post_id}")
	public ResponseEntity<Map<String, Object>> getPostDetail(@PathVariable("post_id") int postId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			PostDto post = postService.getPostDetail(postId);

			resultMap.put("post", post);
			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "사용자 등록 레시피 수정", description = "선택한 포스트 내용을 수정한다.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PutMapping(path = "/modify", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<Map<String, Object>> modifyPost(@Parameter(description = "유저 ID") Principal userId,
			@Parameter(description = "포스트 ID") @RequestParam("postId") int postId,
			@Parameter(description = "수정할 이미지 제목") @RequestParam("title") String title,
			@Parameter(description = "수정할 레시피 이미지들", required = true) @RequestParam("images") List<MultipartFile> images) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			postService.modifyPost(userId, postId, title, images);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "사용자 등록 레시피 삭제", description = "선택한 포스트 내용을 삭제한다.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@DeleteMapping(path = "/delete/{postId}")
	public ResponseEntity<Map<String, Object>> deletePost(@Parameter(description = "유저 ID") Principal userId,
			@PathVariable("postId") int postId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			postService.deletePost(userId, postId);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "포스트 글 좋아요 또는 싫어요 처리", description = "포스트 글의 좋아요 또는 싫어요 여부를 갱신", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PostMapping(path = "/like")
	public ResponseEntity<Map<String, Object>> setLike(@Parameter(description = "로그인한 유저") Principal userId,
			@Parameter(description = "포스트 글 번호") @RequestParam("postId") int postId,
			@Parameter(description = "good 또는 hate") @RequestParam("like") String like) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			postService.setLike(userId, postId, like);

			resultMap.put(MESSAGE, SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "구독한 사람의 컨텐츠를 불러오기", description = "내가 구독한 사람의 컨텐츠를 최신순으로 반환합니다.", security = {
			@SecurityRequirement(name = " X-AUTH-TOKEN") })
	@GetMapping(path = "/subscriberContents/{page}/{size}/{userId}")
	public ResponseEntity<Map<String, Object>> subscriberContents(@PathVariable("page") int page,
			@PathVariable("size") int size, @PathVariable("userId") int userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			List<PostDto> post = postService.subscriberContents(page, size, userId);
			resultMap.put(MESSAGE, SUCCESS);
			resultMap.put("post", post);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put(MESSAGE, FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

}
