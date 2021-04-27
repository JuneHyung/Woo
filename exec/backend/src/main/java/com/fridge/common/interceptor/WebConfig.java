package com.fridge.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	// 예외 처리할 path 작성
		private static final String[] INCLUDE_PATHS = { "" };

		@Autowired
		private JwtInterceptor jwtInterceptor;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(jwtInterceptor).addPathPatterns(INCLUDE_PATHS);	// 기본 적용 경로
//			registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**", "/article/**", "/memo/**") // 기본 적용 경로
//	        .excludePathPatterns(Arrays.asList("/user/confirm/**", "/article/list"));// 적용 제외 경로
		}

	//  Interceptor를 이용해서 처리하므로 전역의 Corss Origin 처리를 해준다.
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD");
//			registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
//					.exposedHeaders("auth-token");
		}
}
