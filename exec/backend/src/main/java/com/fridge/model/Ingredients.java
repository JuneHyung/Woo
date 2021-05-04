package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fridge.model.dto.IngredientsDto;

@Table(name = "ingredients")
@Entity
public class Ingredients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String expired;
	int locx;
	int locy;
	@ManyToOne
	private Fridge fridge;
	@ManyToOne
	private Ingredientsdetail ingredientsdetail;

	protected Ingredients() {
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
