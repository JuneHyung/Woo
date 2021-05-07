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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.Post;
import com.fridge.model.User;
import com.fridge.model.dto.MessageDto;
import com.fridge.model.UserInterest;
import com.fridge.model.dto.PostDto;
import com.fridge.model.repository.PostRepository;
import com.fridge.model.repository.UserInterestRepository;
import com.fridge.model.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	public static final Logger logger = LoggerFactory.getLogger(PostService.class);
	public static final String POST_PATH = "fridge/post/";
	@Autowired
	private KafkaProducerService kafkaProducerServiceImpl; 
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInterestRepository userInterestRepository;

	public String makePath(int id, int num) {
		return POST_PATH + id + "_" + num + ".png";
	}

	public void createFile(int id, int imageCnt, List<MultipartFile> images) throws Exception {
		// 이미지 저장
		for (int i = 0; i < imageCnt; i++) {
			String filePath = makePath(id, i);

			// file image가 없을 경우
			File dest = new File(filePath);

			if (!dest.createNewFile())
				throw new Exception("파일 생성 실패");

			try (FileOutputStream fout = new FileOutputStream(dest)) {
				fout.write(images.get(i).getBytes());
			} catch (Exception e) {
				throw new Exception("파일 입출력 실패!!");
			}
		}
	}

	public void deleteFile(int id, int imageCnt) throws Exception {
		for (int i = 0; i < imageCnt; i++) {
			String filePath = makePath(id, i);

			File deleteFile = new File(filePath);

			if (deleteFile.exists()) {
				if (!deleteFile.delete())
					throw new Exception("파일 삭제 실패");
			} else {
				throw new Exception("파일이 존재하지 않습니다.");
			}
		}
	}

	@Override
	public void upload(String title, List<MultipartFile> images, Principal id) throws Exception {
		Optional<User> user = userRepository.findById(Integer.parseInt(id.getName()));

		Post now = postRepository.save(new Post(title, images.size(), user.get().getNick(), user.get()));
		if (now == null) {
			throw new SQLException("DB insert Error!!!!");
		}
		kafkaProducerServiceImpl.sendMessage(new MessageDto(now.getUser().getId(),now.getId(),now.getUser().getNick()));
		createFile(now.getId(), now.getImagecnt(), images);
	}

	@Override
	public List<PostDto> getPostList(int page, int size) throws Exception {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("date").descending());
		List<Post> posts = postRepository.findAll(pageRequest).getContent();

		List<PostDto> postList = new ArrayList<PostDto>();
		for (Post post : posts) {
			PostDto postDto = new PostDto();

			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setDate(post.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss")));
			postDto.setImageCnt(post.getImagecnt());
			postDto.setVisit(post.getVisit());
			postDto.setGood(post.getGood());
			postDto.setHate(post.getHate());
			postDto.setUser_name(post.getUser_name());

			String filePath = makePath(post.getId(), 0);

			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			postDto.setImageStrArr(new String[] { Base64.getEncoder().encodeToString(fileContent) });

			postList.add(postDto);
		}

		return postList;
	}

	@Override
	public PostDto getPostDetail(int postId) throws Exception {
		Optional<Post> post = postRepository.findById(postId);

		if (!post.isPresent())
			throw new Exception("찾으시는 레시피가 없습니다.");

		PostDto postDto = null;
		postDto = new PostDto();
		postDto.setId(postId);
		postDto.setTitle(post.get().getTitle());
		postDto.setDate(post.get().getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss")));
		postDto.setImageCnt(post.get().getImagecnt());
		postDto.setVisit(post.get().getVisit() + 1);
		postDto.setGood(post.get().getGood());
		postDto.setHate(post.get().getHate());
		postDto.setUser_name(post.get().getUser_name());

		String[] imageStrArr = new String[postDto.getImageCnt()];

		for (int i = 0; i < postDto.getImageCnt(); i++) {
			String filePath = makePath(postDto.getId(), i);
			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			imageStrArr[i] = Base64.getEncoder().encodeToString(fileContent);
		}
		postDto.setImageStrArr(imageStrArr);
		
		postRepository.save(new Post(post.get(), post.get().getVisit() + 1));

		return postDto;
	}

	@Override
	public void modifyPost(Principal userId, int postId, String title, List<MultipartFile> images) throws Exception {
		Post post = postRepository.findByIdAndUser_id(postId, Integer.parseInt(userId.getName()));
		int deleteCnt = post.getImagecnt();

		Post modifyPost = new Post(postId, title, images.size(), post.getUser_name(), post.getUser());
		postRepository.save(modifyPost);

		deleteFile(postId, deleteCnt);
		createFile(postId, images.size(), images);
	}

	@Override
	public void deletePost(Principal userId, int postId) throws Exception {
		Post deletePost = postRepository.findByIdAndUser_id(postId, Integer.parseInt(userId.getName()));

		if (deletePost == null)
			throw new Exception("찾으시는 포스트가 없습니다.");

		System.out.println(deletePost);
		int id = deletePost.getId();
		int imgCnt = deletePost.getImagecnt();
		postRepository.delete(deletePost);

		try {
			deleteFile(id, imgCnt);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public void setLike(Principal user_id, int post_id, String good) throws Exception {
		UserInterest userInterest = userInterestRepository.findByUser_idAndPost_id(Integer.parseInt(user_id.getName()), post_id);
		boolean interest = true;
		
		Post post = postRepository.findById(post_id).get();
		int goodCnt = post.getGood();
		int hateCnt = post.getHate();
		
		if("good".equals(good))
			interest = true;
		else
			interest = false;
		
		
		// 좋아요나 싫어요를 표시한 적 없는 경우
		if(userInterest == null) {
			userInterestRepository.save(new UserInterest(Integer.parseInt(user_id.getName()), post_id, interest));
			if(interest)
				post = new Post(post, goodCnt + 1, hateCnt);
			else
				post = new Post(post, goodCnt, hateCnt + 1);
			postRepository.save(post);
		} else {
			UserInterest updateInterest = new UserInterest(userInterest.getId() ,Integer.parseInt(user_id.getName()), post_id, interest);
			
			if(userInterest.isInterest()) {	// 좋아요를 눌러 둔 경우
				if(interest) { // 좋아요를 한번 더 누른 경우 컬럼 삭제
					userInterestRepository.delete(userInterest);
					post = new Post(post, goodCnt - 1, hateCnt);
				}
				else { 	// 싫어요를 누른 경우 update
					userInterestRepository.save(updateInterest);
					post = new Post(post, goodCnt - 1, hateCnt + 1);
				}
			} else {
				if(!interest) {	// 싫어요를 두번째 누른 경우
					userInterestRepository.delete(userInterest);
					post = new Post(post, goodCnt, hateCnt - 1);
				}
				else {
					userInterestRepository.save(updateInterest);
					post = new Post(post, goodCnt + 1, hateCnt - 1);
				}
			}
			
			postRepository.save(post);
		}
	}

}
