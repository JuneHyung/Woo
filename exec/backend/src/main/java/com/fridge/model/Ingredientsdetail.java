package com.fridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "ingredientsdetail")
@Entity
@Schema(name = "재료 상세 정보", description = "재료의 상세 정보 저장 Entity")
public class Ingredientsdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "고유 ID - PK")
	int id;
	@Schema(title = "재료 이름")
	@Column(nullable = false, length = 20)
	String name;
	@Schema(title = "재료 카테고리")
	@Column(nullable = false, length = 45)
	String category;
	@Schema(title = "재료 이미지")
	@Column(nullable = false, length = 45)
	String image;

	protected Ingredientsdetail() {
	}

	public Ingredientsdetail(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "Ingredientsdetail [id=" + id + ", name=" + name + ", category=" + category + ", image=" + image + "]";
	}

}
