package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Table(name = "subscribe")
@Entity
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	private User user;
	
	int subscribe_id;

	public Subscribe(int id, User user, int subscribe_id) {
		super();
		this.id = id;
		this.user = user;
		this.subscribe_id = subscribe_id;
	}

	public Subscribe() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSubscribe_id() {
		return subscribe_id;
	}

	public void setSubscribe_id(int subscribe_id) {
		this.subscribe_id = subscribe_id;
	}

	@Override
	public String toString() {
		return "Subscribe [id=" + id + ", user=" + user + ", subscribe_id=" + subscribe_id + "]";
	}
	
}
