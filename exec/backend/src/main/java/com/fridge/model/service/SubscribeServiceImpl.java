package com.fridge.model.service;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.common.error.WrongFormException;
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
	public void insertSubscribe(Principal userId, int subscribeId) throws WrongFormException {
		if (subscribeRepository.findByUserIdAndSubscribeId(Integer.parseInt(userId.getName()), subscribeId).isPresent())
			throw new WrongFormException("이미 구독한 사람입니다");

		subscribeRepository.save(new Subscribe(Integer.parseInt(userId.getName()), subscribeId));
	}

	@Override
	public List<UserDto> getSubscribe(Principal userId) {
		List<Subscribe> list = subscribeRepository.findByUserId(Integer.parseInt(userId.getName()));

		List<UserDto> subList = new LinkedList<>();
		for (Subscribe sub : list) {
			UserDto userDto = new UserDto();
			userDto.setId(sub.getSubscribeUser().getId());
			userDto.setEmail(sub.getSubscribeUser().getEmail());
			userDto.setNick(sub.getSubscribeUser().getNick());

			subList.add(userDto);
		}

		return subList;
	}

	@Override
	public void deleteSubscribe(Principal userId, int subscribeId) throws WrongFormException {
		int subscribe = subscribeRepository.findByUserIdAndSubscribeId(Integer.parseInt(userId.getName()), subscribeId)
				.orElseThrow(() -> new WrongFormException("구독하지 않은 사용자를 삭제 시도하였습니다"));

		subscribeRepository.deleteById(subscribe);
	}

}
