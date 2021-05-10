package com.fridge.model.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {
	int userId;
	int postId;
	String name;
	
	public MessageDto(int userId, int postId, String name) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.name = name;
	}
	public int getUser_id() {
		return userId;
	}
	public void setUser_id(int userId) {
		this.userId = userId;
	}
	public int getPost_id() {
		return postId;
	}
	public void setPost_id(int postId) {
		this.postId = postId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Message [userId=" + userId + ", postId=" + postId + ", name=" + name + "]";
	}
}