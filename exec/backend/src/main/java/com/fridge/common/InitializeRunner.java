package com.fridge.common;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeRunner implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(InitializeRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		// 실행 시 폴더가 없으면 폴더 생성
		String filePath = "fridge";
		File folder = new File(filePath);

		if (!folder.exists()) {
			try {
				folder.mkdir();
				logger.info("make a fridge folder");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		filePath += "/post";
		folder = new File(filePath);

		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				logger.info("make a fridge/post folder");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
