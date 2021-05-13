package com.fridge.model.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fridge.common.error.LoginErrorException;
import com.fridge.common.error.WrongFormException;
import com.fridge.common.error.WrongPasswordException;
import com.fridge.model.CustomUserDetail;
import com.fridge.model.User;
import com.fridge.model.dto.UserDto;
import com.fridge.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public Integer login(User user) throws LoginErrorException {
		if (user.getEmail() == null || user.getPwd() == null)
			throw new LoginErrorException("ID 또는 비밀번호를 입력해주세요");

		Optional<User> login = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());

		login.orElseThrow(() -> new LoginErrorException());

		return login.get().getId();
	}

	@Override
	public void join(User user) throws WrongFormException {
		if (user.getEmail() == null || user.getPwd() == null || user.getNick() == null)
			throw new WrongFormException();

		userRepository.save(user);
	}

	@Override
	public void modify(Principal loginId, User modifyUser) throws WrongPasswordException {
		Optional<User> user = userRepository.findByIdAndPwd(Integer.parseInt(loginId.getName()), modifyUser.getPwd());

		user.orElseThrow(() -> new WrongPasswordException());

		userRepository.save(new User(Integer.parseInt(loginId.getName()), modifyUser.getEmail(), modifyUser.getPwd(),
				modifyUser.getNick()));
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findById(Integer.parseInt(username));

		user.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

		return new CustomUserDetail(user.get());
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserInfo(String id) throws WrongFormException {
		Optional<User> user = userRepository.findById(Integer.parseInt(id));

		user.orElseThrow(() -> new WrongFormException("잘못 된 아이디 입니다."));

		UserDto userDto = new UserDto();
		userDto.setId(user.get().getId());
		userDto.setEmail(user.get().getEmail());
		userDto.setNick(user.get().getNick());

		return userDto;
	}

	@Override
	@Transactional(readOnly = true)
	public void checkEmail(String email) throws DuplicateKeyException {
		userRepository.findByEmail(email).ifPresent(user -> {
			throw new DuplicateKeyException(user.getEmail() + "는 이미 존재하는 이메일입니다.");
		});
	}

	@Override
	@Transactional(readOnly = true)
	public void checkNick(String nick) throws DuplicateKeyException {
		userRepository.findByNick(nick).ifPresent(user -> {
			throw new DuplicateKeyException("사용 중인 닉네임입니다.");
		});
	}

	@Override
	public void changPwd(Principal userId, String legacyPwd, String newPwd) throws WrongPasswordException {
		Optional<User> user = userRepository.findByIdAndPwd(Integer.parseInt(userId.getName()), legacyPwd);

		user.orElseThrow(() -> new WrongPasswordException());

		userRepository.save(new User(user.get().getId(), user.get().getEmail(), newPwd, user.get().getNick()));
	}

}
