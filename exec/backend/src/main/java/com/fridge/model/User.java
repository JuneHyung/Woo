package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "user")
@Entity
@Schema(name = "회원정보", description = "우냉부 사용자들의 정보를 담을 수 있는 Entity")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "user pk")
	int id;
	@Schema(title = "이메일")
	String email;
	@Schema(title = "비밀번호")
	String pwd;
	@Schema(title = "닉네임")
	String nick;
	
	public User() {
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public User(String email, String pwd, String nick) {
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
	}
	
	public User(int id, String email, String pwd, String nick) {
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.nick = nick;
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
		return "User [id=" + id + ", email=" + email + ", pwd=" + pwd + ", nick=" + nick + "]";
	}
}