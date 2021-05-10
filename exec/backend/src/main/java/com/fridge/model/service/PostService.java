package com.fridge.model.service;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.dto.PostDto;

public interface PostService {
	void upload(String title, List<MultipartFile> images, Principal id) throws Exception;

	List<PostDto> getPostList(int page, int size) throws Exception;

	List<PostDto> getMyPosLlist(int page, int size, int userId) throws Exception;

	PostDto getPostDetail(int postId) throws Exception;

	void deletePost(Principal userId, int postId) throws Exception;

	void modifyPost(Principal userId, int postId, String title, List<MultipartFile> images) throws Exception;

	void setLike(Principal userId, int postId, String good) throws Exception;
}
