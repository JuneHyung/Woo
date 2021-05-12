package com.fridge.common.error;

public class WrongPasswordException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public WrongPasswordException() {
		super("잘 못된 비밀번호 입니다");
	}
}
