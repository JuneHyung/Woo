package com.fridge.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.config.security.JwtTokenProvider;
import com.fridge.model.User;
import com.fridge.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
	public String login(
			@RequestBody @Parameter(name="로그인 시 필요한 회원정보(이메일, 비밀번호)", required = true) User user){
		User loginMember = null;
		try {
			loginMember = userService.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (loginMember == null) { // 로그인 정보가 존재하는 경우
			throw new IllegalArgumentException("ID, 비밀번호를 다시 확인해주세요.");
		}

		return jwtTokenProvider.createToken(loginMember.getUsername());
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


	


}
