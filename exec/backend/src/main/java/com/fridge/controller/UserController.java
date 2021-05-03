package com.fridge.controller;

import java.security.Principal;
//dummy
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.config.security.JwtTokenProvider;
import com.fridge.model.User;
import com.fridge.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserController v0.1")
@RestController
@RequestMapping("/user")
public class UserController {
	// Server side log를 위한 객체 생성
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService; // 데이터베이스에 로그인 시도를 위한 service

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Operation(summary = "로그인", description = "Access-token과 로그인 결과 메시지를 반환한다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @Parameter(name = "로그인 시 필요한 회원정보(이메일, 비밀번호)", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.OK;
		User loginMember = null;
		try {
			loginMember = userService.login(user);
			resultMap.put("X-AUTH-TOKEN", jwtTokenProvider.createToken(Integer.toString(loginMember.getId())));
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// unb 회원가입
	@Operation(summary = "회원 가입", description = "회원 가입 결과를 반환한다.")
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> join(
			@RequestBody @Parameter(name = "회원 가입에 필요한 회원 정보", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			userService.join(user);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("massage", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "로그인 회원 정보 제공", description = "로그인한 회원의 정보를 제공", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getUserInfo(@Parameter(name = "로그인 회원") Principal user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.OK;

		System.out.println(user.getName());
		try {
			User userInfo = userService.getUserInfo(user.getName());

			resultMap.put("user", userInfo);
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "회원 정보 수정", description = "수정할 정보를 받아 회원 정보 수정", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(
			@Parameter(name = "로그인 회원 PK") Principal loginUser,
			@Parameter(name = "수정할 회원 정보", required = true) @RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			user.setId(Integer.parseInt(loginUser.getName()));
			userService.modify(user);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "회원 탈퇴", description = "회원 id를 받아서 회원 탈퇴", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> delete(@Parameter(name = "탈퇴할 회원") Principal user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			userService.delete(Integer.parseInt(user.getName()));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
