package com.fridge.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PostService {
	void upload(String title, List<MultipartFile> images, int id) throws Exception;
}
