package com.fridge.model;
/**
 *User Dto 작성
 *
 *@author 송원준 작성일 : 2021-04-26
*/

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "회원정보", description = "우냉부 사용자들의 정보를 담을 수 있는 Model")
public class UserDto {
	@Schema(title = "user pk")
	private int id;
	@Schema(title = "이메일")
	private String email;
	@Schema(title = "비밀번호")
	private String pwd;
	@Schema(title = "닉네임")
	private String nick;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
}
