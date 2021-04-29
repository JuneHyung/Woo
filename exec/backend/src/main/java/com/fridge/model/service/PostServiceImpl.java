package com.fridge.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.Post;
import com.fridge.model.User;
import com.fridge.model.repository.PostRepository;
import com.fridge.model.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	public static final Logger logger = LoggerFactory.getLogger(PostService.class);
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void upload(String title, List<MultipartFile> images, int id) throws Exception {
		Post post = new Post();
		Optional<User> user = userRepository.findById(id);

		post.setTitle(title);
		post.setContents("....");
		post.setDate(LocalDateTime.now());
		post.setImagecnt(images.size());
		post.setUser_name(user.get().getNick());
		post.setUser(user.get());

		Post now = postRepository.save(post);
		if(now == null) {
			logger.error("DB insert Error!!!!");
			throw new SQLException();
		}
		
		for (int i = 0; i < images.size(); i++) {
			String path = "post/" + now.getId() + "_" + i + ".png";
			
			// file image가 없을 경우
			File dest = new File(path);
			dest.createNewFile();
			
			try (FileOutputStream fout = new FileOutputStream(dest)) {
				fout.write(images.get(0).getBytes());
			} catch (Exception e) {
				logger.error("파일 입출력 실패!!");
				throw new RuntimeException();
			}
		}
		
	}

}
