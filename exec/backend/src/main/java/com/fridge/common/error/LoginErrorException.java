package com.fridge.common.error;

public class LoginErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public LoginErrorException() {
		super("로그인 실패");
	}
	
	public LoginErrorException(String message) {
		super(message);
	}
}
