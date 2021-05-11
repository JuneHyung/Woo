package com.fridge.model.service;

import java.util.List;

import com.fridge.model.dto.MessageDto;

public interface KafkaConsumerService {
	public List<MessageDto> getMessage() throws Exception;
}
