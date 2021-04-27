package com.fridge.common.error;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException() {
		super("계정 권한이 유효하지 않습니다.\n다시 로그인을 해주세요.");
	}
}
