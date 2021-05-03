package com.fridge.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.Post;
import com.fridge.model.User;
import com.fridge.model.dto.PostDto;
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
	public void upload(String title, List<MultipartFile> images, Principal id) throws Exception {
		Optional<User> user = userRepository.findById(Integer.parseInt(id.getName()));

		Post now = postRepository.save(new Post(title, images.size(), user.get().getNick(), user.get()));
		if (now == null) {
			logger.error("DB insert Error!!!!");
			throw new SQLException();
		}
		// 최초 실행 시 폴더가 없기에 폴더 생성
		String filePath = "fridge";
		File folder = new File(filePath);

		if (!folder.exists()) {
			try {
				folder.mkdir();
				System.out.println(filePath + " 폴더 생성 완료!!");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		filePath += "/post";
		folder = new File(filePath);

		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		// 이미지 저장
		for (int i = 0; i < images.size(); i++) {
			String path = "fridge/post/" + now.getId() + "_" + i + ".png";

			// file image가 없을 경우
			File dest = new File(path);
			dest.createNewFile();

			try (FileOutputStream fout = new FileOutputStream(dest)) {
				fout.write(images.get(i).getBytes());
			} catch (Exception e) {
				logger.error("파일 입출력 실패!!");
				throw new RuntimeException();
			}
		}

	}

	@Override
	public List<PostDto> getPostList() throws Exception {
		List<Post> posts = postRepository.findAllByOrderByDateDesc();
		
		List<PostDto> postList = new ArrayList<PostDto>();
		for(Post post : posts) {
			PostDto postDto = new PostDto();
			
			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setDate(post.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss")));
			postDto.setImageCnt(post.getImagecnt());
			postDto.setVisit(post.getVisit());
			postDto.setGood(post.getGood());
			postDto.setHate(post.getHate());
			postDto.setUser_name(post.getUser_name());
			
			String fileName = Integer.toString(post.getId()) + "_0.png";
			String filePath = "fridge/post/" + fileName;
			
			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			postDto.setImageStrArr(new String[] {Base64.getEncoder().encodeToString(fileContent)});
			
			postList.add(postDto);
		}
		
		System.out.println(postList);
		
		return postList;
	}
}
