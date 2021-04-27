package com.fridge.config.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	private String secretKey = "fridgeKey";
	// 만료 시간
	private final int EXPIRE_MINUTES = 60;
	
	@Autowired
	private UserDetailsService userDetailsService;

	// 객체 초기화 , secretKey를 Base64로 인코딩
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	// JWT 토큰 생성
	public String createToken(String userPK) {
		Claims claims = Jwts.claims().setSubject(userPK);
		Date now = new Date();

		return Jwts.builder().setClaims(claims) // 정보 저장
				.setIssuedAt(now) // 토큰 발생 시간 정보
				.setExpiration(new Date(now.getTime() + 1000 * 60 * EXPIRE_MINUTES)) // set Expire Time
				.signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature에 들어갈 secret 값 세팅
				.compact();
	}
	
	// JWT 토큰에서 인증 정보 조회
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	// 토큰에서 회원 정보 추출
	public String getUserPK(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN 값"
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	// 토큰의 유효성 + 만료일자 확인
	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
