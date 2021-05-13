package com.fridge.model.dto;

public class MessageDto {
	int userId;
	int postId;
	String name;

	public MessageDto() {
	}

	public MessageDto(int userId, int postId, String name) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
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