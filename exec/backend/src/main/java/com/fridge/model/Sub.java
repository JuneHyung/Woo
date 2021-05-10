package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "sub")
@Schema(name = "서브 레시피 Table", description = "부 재료로 이용해서 만들 수 있는 레시피 정보 Entity")
public class Sub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "고유 ID - PK")
	private int id;
	@ManyToOne
	@Schema(title = "만들 수 있는 레시피 - FK")
	private Recipe recipe;
	@ManyToOne
	@Schema(title = "재료 상세 정보 - FK")
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
		return "Sub [id=" + id + ", recipe=" + recipe + ", ingredientsdetail=" + ingredientsdetail + "]";
	}
}
