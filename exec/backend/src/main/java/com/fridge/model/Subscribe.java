package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Table(name = "subscribe")
@Entity
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
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
