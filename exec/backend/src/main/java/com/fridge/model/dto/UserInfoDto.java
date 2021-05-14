package com.fridge.model.dto;

public class UserInfoDto {
	int id;
	String email;
	String pwd;
	String nick;

	public UserInfoDto(int id, String email, String pwd, String nick) {
		super();
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
	}

	public UserInfoDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return pwd;
	}

	public String getNick() {
		return nick;
	}

	@Override
	public String toString() {
		return "LoginDto [id=" + id + ", email=" + email + ", pwd=" + pwd + ", nick=" + nick + "]";
	}

}
