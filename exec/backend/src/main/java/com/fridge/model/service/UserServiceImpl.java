package com.fridge.model.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fridge.model.CustomUserDetail;
import com.fridge.model.User;
import com.fridge.model.dto.UserDto;
import com.fridge.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Integer login(User user) throws Exception {
		if (user.getEmail() == null || user.getPwd() == null)
			return null;

		return userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd()).getId();
	}

	@Override
	public void join(User user) throws Exception {
		userRepository.save(user);
	}

	@Override
	public void checkEmail(String email) throws DuplicateKeyException {
		if (userRepository.findByEmail(email).isPresent())
			throw new DuplicateKeyException("이미 있는 가입되어 있는 이메일입니다.");
	}

	@Override
	public void checkNick(String nick) throws DuplicateKeyException {
		if (userRepository.findByNick(nick).isPresent())
			throw new DuplicateKeyException("사용 중인 닉네임입니다.");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(Integer.parseInt(username)).get();
		if (user == null)
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");

		return new CustomUserDetail(user);
	}

	@Override
	public void changPwd(Principal userId, String legacyPwd, String newPwd) throws Exception {
		User user = userRepository.findByIdAndPwd(Integer.parseInt(userId.getName()), legacyPwd);

		if (user == null)
			throw new Exception("비밀번호가 잘 못 되었습니다");

		userRepository.save(new User(user.getId(), user.getEmail(), newPwd, user.getNick()));
	}

	@Override
	public void modify(Principal loginId, User modifyUser) throws Exception {
		User user = userRepository.findByIdAndPwd(Integer.parseInt(loginId.getName()), modifyUser.getPwd());

		if (user == null)
			throw new Exception("비밀번호가 잘 못 되었습니다");

		userRepository
				.save(new User(Integer.parseInt(loginId.getName()), user.getEmail(), user.getPwd(), user.getNick()));
	}

	@Override
	public void delete(int id) throws Exception {
		userRepository.deleteById(id);
	}

	@Override
	public UserDto getUserInfo(String id) throws Exception {
		Optional<User> user = userRepository.findById(Integer.parseInt(id));
		if (!user.isPresent())
			throw new Exception("잘못 된 아이디 입니다.");

		UserDto userDto = new UserDto();
		userDto.setId(user.get().getId());
		userDto.setEmail(user.get().getEmail());
		userDto.setNick(user.get().getNick());

		return userDto;
	}

}
