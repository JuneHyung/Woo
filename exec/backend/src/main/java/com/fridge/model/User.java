package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "user")
@Entity
@Schema(name = "회원정보", description = "우냉부 사용자들의 정보를 담을 수 있는 Model")
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pwd=" + pwd + ", nick=" + nick + "]";
	}
}
