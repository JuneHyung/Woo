package com.fridge.model.service;

import java.io.IOException;
import java.security.Principal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fridge.model.dto.MessageDto;
import com.fridge.model.repository.SubscribeRepository;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	@Autowired
	private KafkaConsumer<String, String> kafkaConsumer;

	@Autowired
	private SubscribeRepository subscribeRepository;

	@Override
	public List<MessageDto> getMessage(Principal userId) throws IOException {
		List<MessageDto> messageList = new ArrayList<>();

		List<TopicPartition> partitions = kafkaConsumer.partitionsFor("exam").stream()
				.map(partitionInfo -> new TopicPartition("exam", partitionInfo.partition()))
				.collect(Collectors.toList());

		kafkaConsumer.assign(partitions);
		kafkaConsumer.seek(partitions.get(0), 0);

		ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
		Set<Integer> subscribeIdSet = subscribeRepository.findSubscribeIdByUserId(Integer.parseInt(userId.getName()));

		for (ConsumerRecord<String, String> record : records) {
			ObjectMapper objectMapper = new ObjectMapper();
			MessageDto message = objectMapper.readValue(record.value(), MessageDto.class);

			if (subscribeIdSet.contains(message.getUserId()))
				messageList.add(message);
		}
		// 최근글이 위로 가기 위해 reverse
		Collections.reverse(messageList);
		return messageList;
	}

}
