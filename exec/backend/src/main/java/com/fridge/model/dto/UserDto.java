package com.fridge.model.dto;

public class UserDto {
	private int id;
	private String email;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", email=" + email + ", nick=" + nick + "]";
	}

}
