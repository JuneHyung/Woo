package com.fridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fridge.model.dto.FridgeDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "fridge")
@Entity
@Schema(name = "냉장고 정보", description = "냉장고 정보를 담기 위한 객체")
public class Fridge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "fridge pk")
	@Column(nullable = false)
	int id;
	@Schema(title = "fridge name")
	@Column(nullable = false, length = 10)
	String name;
	@Schema(title = "fridge type")
	@Column(nullable = false)
	int type;
	@ManyToOne
	@Schema(title = "fridge fk")
	private User user;

	protected Fridge() {
	}

	public Fridge(int fridgeId) {
		this.id = fridgeId;
	}

	public Fridge(FridgeDto fridgeDto, int userId) {
		this.name = fridgeDto.getName();
		this.type = fridgeDto.getType();
		this.user = new User(userId);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Fridge [id=" + id + ", name=" + name + ", type=" + type + ", user=" + user + "]";
	}

}
