package com.fridge.model.service;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Subscribe;
import com.fridge.model.dto.UserDto;
import com.fridge.model.repository.SubscribeRepository;
import com.fridge.model.repository.UserRepository;

@Service
public class SubscribeServiceImpl implements SubscribeService {
	@Autowired
	SubscribeRepository subscribeRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void insertScribe(Principal userId, int subscribeId) throws Exception {
		List<Subscribe> list = subscribeRepository.findByUser_id(Integer.parseInt(userId.getName()));
		for (Subscribe subscribe : list) {
			if (subscribe.getSubscribe_user().getId() == subscribeId)
				throw new Exception("이미 구독한 사람입니다");
		}

		subscribeRepository.save(new Subscribe(Integer.parseInt(userId.getName()), subscribeId));
	}

	@Override
	public List<UserDto> getScribe(Principal userId) throws Exception {
		List<Subscribe> list = subscribeRepository.findByUser_id(Integer.parseInt(userId.getName()));

		List<UserDto> subList = new LinkedList<>();
		for (Subscribe sub : list) {
			UserDto userDto = new UserDto();
			userDto.setId(sub.getSubscribe_user().getId());
			userDto.setEmail(sub.getSubscribe_user().getEmail());
			userDto.setNick(sub.getSubscribe_user().getNick());

			subList.add(userDto);
		}

		return subList;
	}

	@Override
	public void deleteScribe(Principal userId, int subscribeId) throws Exception {
		Optional<Integer> subscribe = subscribeRepository.findByUserIdAndSubscribeId(Integer.parseInt(userId.getName()),
				subscribeId);
		if (!subscribe.isPresent())
			throw new Exception("구독하지 않은 사용자를 삭제 시도하였습니다");

		subscribeRepository.deleteById(subscribe.get());
	}

}
