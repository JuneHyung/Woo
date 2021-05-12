package com.fridge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fridge.model.dto.IngredientsDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "ingredients")
@Entity
@Schema(name = "재료 정보", description = "재료 정보를 저장하는 Entity")
public class Ingredients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "고유 ID - PK")
	@Column(nullable = false)
	int id;
	@Schema(title = "유통 기한")
	@Column(nullable = true)
	String expired;
	@Schema(title = "냉장고 X 좌표")
	@Column(nullable = false)
	int locx;
	@Schema(title = "냉장고 Y 좌표")
	@Column(nullable = false)
	int locy;
	@ManyToOne
	@Schema(title = "냉장고 정보 - FK")
	private Fridge fridge;
	@ManyToOne
	@Schema(title = "재료 상세 정보 - FK")
	private Ingredientsdetail ingredientsdetail;

	protected Ingredients() {
	}
	
	public Ingredients(int id, String expired, int locx, int locy, Fridge fridge, Ingredientsdetail ingredientsdetail) {
		super();
		this.id = id;
		this.expired = expired;
		this.locx = locx;
		this.locy = locy;
		this.fridge = fridge;
		this.ingredientsdetail = ingredientsdetail;
	}

	public Ingredients(IngredientsDto ingredientsDto) {
		this.id = ingredientsDto.getId();
		this.expired = ingredientsDto.getExpired();
		this.locx = ingredientsDto.getLocx();
		this.locy = ingredientsDto.getLocy();
		this.fridge = new Fridge(ingredientsDto.getFridgeId());
		this.ingredientsdetail = new Ingredientsdetail(ingredientsDto.getIngredientsDetailId());
	}

	public int getId() {
		return id;
	}

	public String getExpired() {
		return expired;
	}

	public int getLocx() {
		return locx;
	}

	public int getLocy() {
		return locy;
	}

	public Fridge getFridge() {
		return fridge;
	}

	public Ingredientsdetail getIngredientsdetail() {
		return ingredientsdetail;
	}

	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", expired=" + expired + ", locx=" + locx + ", locy=" + locy + ", fridge="
				+ fridge + ", ingredientsdetail=" + ingredientsdetail + "]";
	}

}
