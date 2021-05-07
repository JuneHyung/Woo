package com.fridge.model.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {
	int user_id;
	int post_id;
	String name;
	
	public MessageDto(int user_id, int post_id, String name) {
		super();
		this.user_id = user_id;
		this.post_id = post_id;
		this.name = name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Message [user_id=" + user_id + ", post_id=" + post_id + ", name=" + name + "]";
	}
}