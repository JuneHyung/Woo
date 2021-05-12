package com.fridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "userinterest")
@Schema(name = "좋아요, 싫어요 table", description = "사용자가 누른 좋아요 혹은 싫어요에 대한 정보를 저장")
public class UserInterest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "좋아요 정보 저장 테이블 식별 키 - PK")
	@Column(nullable = false)
	private int id;
	@ManyToOne
	@Schema(title = "유저 ID - FK")
	private User user;
	@ManyToOne
	@Schema(title = "글 번호 - FK")
	private Post post;
	@Schema(title = "좋아요 또는 싫어요 여부", description = "true : 좋아요  false : 싫어요")
	@Column(nullable = false)
	private boolean interest;

	protected UserInterest() {
	}

	public UserInterest(int userId, int postId, boolean interest) {
		this.user = new User(userId);
		this.post = new Post(postId);
		this.interest = interest;
	}

	public UserInterest(int id, int userId, int postId, boolean interest) {
		this.id = id;
		this.user = new User(userId);
		this.post = new Post(postId);
		this.interest = interest;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Post getPost() {
		return post;
	}

	public boolean isInterest() {
		return interest;
	}

	@Override
	public String toString() {
		return "UserInterest [id=" + id + ", user=" + user + ", post=" + post + ", interest=" + interest + "]";
	}

}
