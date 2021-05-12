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

@Entity
@Table(name = "main")
@Schema(description = "메인 재료로 사용하여 만들 수 있는 레시피 저장 Entity")
public class Main {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "고유 ID - PK")
	@Column(nullable = false)
	private int id;
	@ManyToOne
	@Schema(title = "레시피 정보 - FK")
	@JoinColumn(nullable = false , name = "recipe_id")
	private Recipe recipe;
	@ManyToOne
	@Schema(title = "메인 재료 상세 정보 - FK")
	@JoinColumn(nullable = false , name = "ingredientsdetail_id")
	private Ingredientsdetail ingredientsdetail;

	public int getId() {
		return id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public Ingredientsdetail getIngredientsdetail() {
		return ingredientsdetail;
	}

	@Override
	public String toString() {
		return "Main [id=" + id + ", recipe=" + recipe + ", ingredientsdetail=" + ingredientsdetail + "]";
	}

}
