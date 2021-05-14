package com.fridge.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.Post;
import com.fridge.model.User;
import com.fridge.model.UserInterest;
import com.fridge.model.dto.MessageDto;
import com.fridge.model.dto.PostDto;
import com.fridge.model.repository.PostRepository;
import com.fridge.model.repository.UserInterestRepository;
import com.fridge.model.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	public static final Logger logger = LoggerFactory.getLogger(PostService.class);
	public static final String POST_PATH = "fridge/post/";
	public static final String DATE_FORMAT = "yyyy-MM-dd hh-mm-ss";

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

	public void createFile(int id, int imageCnt, List<MultipartFile> images) throws IOException {
		// 이미지 저장
		for (int i = 0; i < imageCnt; i++) {
			String filePath = makePath(id, i);

			// file image가 없을 경우
			File dest = new File(filePath);

			if (!dest.createNewFile())
				throw new IOException("파일 생성 실패");

			try (FileOutputStream fout = new FileOutputStream(dest)) {
				fout.write(images.get(i).getBytes());
			} catch (Exception e) {
				throw new IOException("파일 입출력 실패!!");
			}
		}
	}

	public void deleteFile(int id, int imageCnt) throws IOException {
		for (int i = 0; i < imageCnt; i++) {
			String filePath = makePath(id, i);

			File deleteFile = new File(filePath);

			if (deleteFile.exists()) {
				if (!deleteFile.delete())
					throw new IOException("파일 삭제 실패");
			} else {
				throw new FileExistsException("파일이 존재하지 않습니다.");
			}
		}
	}

	@Override
	public void upload(String title, List<MultipartFile> images, Principal id) throws IOException, SQLException {
		User user = userRepository.findById(Integer.parseInt(id.getName()))
				.orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

		Post now = Optional.ofNullable(postRepository.save(new Post(title, images.size(), user.getNick(), user)))
				.orElseThrow(() -> new SQLException("DB insert Error!!!!"));

		kafkaProducerServiceImpl
				.sendMessage(new MessageDto(now.getUser().getId(), now.getId(), now.getUser().getNick()));
		createFile(now.getId(), now.getImagecnt(), images);
	}

	@Override
	public List<PostDto> getPostList(int page, int size) throws IOException {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("date").descending());
		List<Post> posts = postRepository.findAll(pageRequest).getContent();

		List<PostDto> postList = new ArrayList<>();
		for (Post post : posts) {
			PostDto postDto = new PostDto();

			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setDate(post.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
			postDto.setImageCnt(post.getImagecnt());
			postDto.setVisit(post.getVisit());
			postDto.setGood(post.getGood());
			postDto.setHate(post.getHate());
			postDto.setWriter(post.getWriter());

			String filePath = makePath(post.getId(), 0);

			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			postDto.setImageStrArr(new String[] { Base64.getEncoder().encodeToString(fileContent) });

			postList.add(postDto);
		}

		return postList;
	}

	@Override
	public List<PostDto> getMyPosLlist(int page, int size, int userId) throws IOException {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("date").descending());
		List<Post> posts = postRepository.findByUserId(userId, pageRequest);

		List<PostDto> postList = new ArrayList<>();
		for (Post post : posts) {
			PostDto postDto = new PostDto();

			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setDate(post.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
			postDto.setImageCnt(post.getImagecnt());
			postDto.setVisit(post.getVisit());
			postDto.setGood(post.getGood());
			postDto.setHate(post.getHate());
			postDto.setWriter(post.getWriter());

			String filePath = makePath(post.getId(), 0);

			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			postDto.setImageStrArr(new String[] { Base64.getEncoder().encodeToString(fileContent) });

			postList.add(postDto);
		}

		return postList;
	}

	@Override
	public PostDto getPostDetail(int postId) throws SQLException, IOException {
		Post post = postRepository.findById(postId).orElseThrow(() -> new SQLException("찾으시는 레시피가 없습니다."));

		PostDto postDto = null;
		postDto = new PostDto();
		postDto.setId(postId);
		postDto.setTitle(post.getTitle());
		postDto.setDate(post.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
		postDto.setImageCnt(post.getImagecnt());
		postDto.setVisit(post.getVisit() + 1);
		postDto.setGood(post.getGood());
		postDto.setHate(post.getHate());
		postDto.setUserId(post.getUser().getId());
		postDto.setWriter(post.getWriter());

		String[] imageStrArr = new String[postDto.getImageCnt()];

		for (int i = 0; i < postDto.getImageCnt(); i++) {
			String filePath = makePath(postDto.getId(), i);
			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			imageStrArr[i] = Base64.getEncoder().encodeToString(fileContent);
		}
		postDto.setImageStrArr(imageStrArr);

		postRepository.save(new Post(post, post.getVisit() + 1));

		return postDto;
	}

	@Override
	public void deletePost(Principal userId, int postId) throws IOException, SQLException {
		Post deletePost = postRepository.findByIdAndUserId(postId, Integer.parseInt(userId.getName()))
				.orElseThrow(() -> new SQLException("삭제하려는 글이 없습니다"));

		int id = deletePost.getId();
		int imgCnt = deletePost.getImagecnt();
		postRepository.delete(deletePost);

		deleteFile(id, imgCnt);
	}

	@Override
	public void modifyPost(Principal userId, int postId, String title, List<MultipartFile> images)
			throws IOException, WrongFormException {
		Post post = postRepository.findByIdAndUserId(postId, Integer.parseInt(userId.getName()))
				.orElseThrow(() -> new WrongFormException("없는 게시글입니다"));
		int deleteCnt = post.getImagecnt();

		Post modifyPost = new Post(postId, title, images.size(), post.getWriter(), post.getUser());
		postRepository.save(modifyPost);

		deleteFile(postId, deleteCnt);
		createFile(postId, images.size(), images);
	}

	@Override
	public void setLike(Principal userId, int postId, String good) throws WrongFormException {
		// 존재 여부에 따라 로직을 다르게 처리해야 하므로 orElseThrow 하지 않음
		Optional<UserInterest> optUserInterest = userInterestRepository
				.findByUserIdAndPostId(Integer.parseInt(userId.getName()), postId);

		Post post = postRepository.findById(postId).orElseThrow(() -> new WrongFormException("선택하신 글을 찾을 수 없습니다"));
		int goodCnt = post.getGood();
		int hateCnt = post.getHate();

		boolean interest = false;
		if ("good".equals(good))
			interest = true;

		// 좋아요나 싫어요를 표시한 적 없는 경우
		if (!optUserInterest.isPresent()) {
			userInterestRepository.save(new UserInterest(Integer.parseInt(userId.getName()), postId, interest));
			if (interest)
				post = new Post(post, goodCnt + 1, hateCnt);
			else
				post = new Post(post, goodCnt, hateCnt + 1);
			postRepository.save(post);

			return;
		}
		UserInterest legacy = optUserInterest.get();
		UserInterest updateInterest = new UserInterest(legacy.getId(), Integer.parseInt(userId.getName()), postId,
				interest);

		if (!(interest ^ legacy.isInterest())) { // 좋아요 or 싫어요를 두번 누른 경우
			userInterestRepository.delete(legacy);
			if (interest) // 종아요를 누른 경우
				post = new Post(post, goodCnt - 1, hateCnt);
			else // 싫어요를 누른 경우
				post = new Post(post, goodCnt, hateCnt - 1);
		} else {
			userInterestRepository.save(updateInterest);
			if (interest) // 종아요를 누른 경우
				post = new Post(post, goodCnt + 1, hateCnt - 1);
			else // 싫어요를 누른 경우
				post = new Post(post, goodCnt - 1, hateCnt + 1);
		}

		postRepository.save(post);
	}

	@Override
	public List<PostDto> subscriberContents(int page, int size, int userId) throws IOException {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("date").descending());
		List<Post> posts = postRepository.findByUserId(userId, pageRequest);

		List<PostDto> postList = new ArrayList<>();

		for (Post post : posts) {
			PostDto postDto = new PostDto();

			postDto.setId(post.getId());
			postDto.setTitle(post.getTitle());
			postDto.setDate(post.getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
			postDto.setImageCnt(post.getImagecnt());
			postDto.setVisit(post.getVisit());
			postDto.setGood(post.getGood());
			postDto.setHate(post.getHate());
			postDto.setWriter(post.getWriter());

			String filePath = makePath(post.getId(), 0);

			byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
			postDto.setImageStrArr(new String[] { Base64.getEncoder().encodeToString(fileContent) });

			postList.add(postDto);
		}

		return postList;
	}

}
