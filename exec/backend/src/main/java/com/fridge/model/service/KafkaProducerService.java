package com.fridge.model.service;

import java.io.IOException;

import com.fridge.model.dto.MessageDto;

public interface KafkaProducerService {
	void sendMessage(MessageDto message) throws IOException;
}
