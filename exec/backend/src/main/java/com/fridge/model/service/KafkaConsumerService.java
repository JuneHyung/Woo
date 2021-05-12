package com.fridge.model.service;

import java.io.IOException;
import java.util.List;

import com.fridge.model.dto.MessageDto;

public interface KafkaConsumerService {
	public List<MessageDto> getMessage() throws IOException;
}
