package com.fridge.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fridge.model.dto.MessageDto;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	private static final String TOPIC = "exam";
	@Autowired
	private KafkaTemplate<String, MessageDto> kafkaTemplate;

	public void sendMessage(MessageDto message) throws IOException {
		this.kafkaTemplate.send(TOPIC, message);
	}

}