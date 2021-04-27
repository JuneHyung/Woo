package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "fridge")
@Entity
@Schema(name = "냉장고 정보", description = "냉장고 정보를 담기 위한 객체")
public class Fridge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "fridge pk")
	int id;
	@Schema(title = "fridge name")
	String name;
	@Schema(title = "fridge type")
	int type;
	@ManyToOne
	@Schema(title = "fridge fk")
	private User user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Fridge [id=" + id + ", name=" + name + ", type=" + type + ", user=" + user + "]";
	}
	
	
}
