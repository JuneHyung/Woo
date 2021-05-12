package com.fridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "subscribe")
@Entity
@Schema(name = "구독 Table", description = "사용자가 누른 구독 정보를 담은 Entity")
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "PK")
	@Column(nullable = false)
	int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@Schema(title = "자기 자신 - FK")
	private User user;
	@ManyToOne
	@JoinColumn(name = "subscribe_id", referencedColumnName = "id")
	@Schema(title = "구독한 사람 ID - FK")
	private User subscribe_user;

	protected Subscribe() {
	}

	public Subscribe(int userId, int subscribeId) {
		this.user = new User(userId);
		this.subscribe_user = new User(subscribeId);
	}

	public Subscribe(User user, User subscribe_user) {
		super();
		this.user = user;
		this.subscribe_user = subscribe_user;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public User getSubscribe_user() {
		return subscribe_user;
	}

	@Override
	public String toString() {
		return "Subscribe [id=" + id + ", user=" + user + ", subscribe_user=" + subscribe_user + "]";
	}

}