package com.fridge.model.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fridge.model.dto.MessageDto;

public interface KafkaConsumerService {
	public List<MessageDto> getMessage() throws Exception;
}
