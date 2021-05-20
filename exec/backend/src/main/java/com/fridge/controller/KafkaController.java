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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.dto.MessageDto;
import com.fridge.model.service.KafkaConsumerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private KafkaConsumerService consumer;

	@GetMapping
	@Operation(summary = "게시글 업로드 알람", description = "등록된 게시글을 Kafka Message Queue에서 가져온다.", security = {
			@SecurityRequirement(name = "X-AUTH-TOKEN") })
	public ResponseEntity<Map<String, Object>> getAlarm(@Parameter(name = "로그인한 유저") Principal userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			List<MessageDto> messageList = consumer.getMessage(userId);

			status = HttpStatus.OK;
			resultMap.put("messageList", messageList);
			resultMap.put(MESSAGE, SUCCESS);
		} catch (Exception e) {
			logger.error("Kafka Consumer Error", e);
			status = HttpStatus.ACCEPTED;
			resultMap.put(MESSAGE, FAIL);
		}

		return new ResponseEntity<>(resultMap, status);
	}
}
