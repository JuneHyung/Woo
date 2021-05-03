package com.fridge.model.service;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fridge.model.dto.PostDto;

public interface PostService {
	void upload(String title, List<MultipartFile> images, Principal id) throws Exception;
	
	List<PostDto> getPostList() throws Exception;

	PostDto getPostDetail(int postId) throws Exception;
}
