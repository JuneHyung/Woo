package com.fridge.model;

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
	int id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@Schema(title = "자기 자신 - FK")
	private User user;
	@Schema(title = "구독한 사람 ID - FK")
	int subscribe_id;

	public Subscribe(User user, int subscribe_id) {
		super();
		this.user = user;
		this.subscribe_id = subscribe_id;
	}

	public Subscribe() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSubscribe_id(int subscribe_id) {
		this.subscribe_id = subscribe_id;
	}

	@Override
	public String toString() {
		return "Subscribe [id=" + id + ", user=" + user + ", subscribe_id=" + subscribe_id + "]";
	}
	
}
