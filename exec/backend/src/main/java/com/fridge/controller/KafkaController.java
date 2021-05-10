package com.fridge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fridge.model.dto.MessageDto;
import com.fridge.model.service.KafkaConsumerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	@Autowired
	private KafkaConsumerService consumer;

	@GetMapping
	@Operation(summary = "게시글 업로드 알람", description = "등록된 게시글을 Kafka Message Queue에서 가져온다.")
	public List<MessageDto> getAlarm() {
		List<MessageDto> messageList = consumer.getMessage();

		return messageList;
	}
}
