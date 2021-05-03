package com.fridge.model.service;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PostService {
	void upload(String title, List<MultipartFile> images, Principal id) throws Exception;
}
