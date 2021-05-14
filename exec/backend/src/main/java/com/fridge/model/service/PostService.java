package com.fridge.model.service;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.dto.PostDto;

public interface PostService {
	void upload(String title, List<MultipartFile> images, Principal id) throws IOException, SQLException;

	List<PostDto> getPostList(int page, int size) throws IOException;

	List<PostDto> getMyPosLlist(int page, int size, int userId) throws IOException;

	PostDto getPostDetail(int postId) throws SQLException, IOException;

	void deletePost(Principal userId, int postId) throws IOException, SQLException;

	void modifyPost(Principal userId, int postId, String title, List<MultipartFile> images)
			throws IOException, WrongFormException;

	void setLike(Principal userId, int postId, String good) throws WrongFormException;

	List<PostDto> subscriberContents(int page, int size, int userId) throws IOException;
}
