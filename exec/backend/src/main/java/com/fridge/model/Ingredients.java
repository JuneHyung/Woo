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
		this.expired = ingredientsDto.getExpired();
		this.locx = ingredientsDto.getLocx();
		this.locy = ingredientsDto.getLocy();
		this.fridge = new Fridge(ingredientsDto.getFridgeId());
		this.ingredientsdetail = new Ingredientsdetail(ingredientsDto.getIngredientsDetailId());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public int getLocx() {
		return locx;
	}

	public void setLocx(int locx) {
		this.locx = locx;
	}

	public int getLocy() {
		return locy;
	}

	public void setLocy(int locy) {
		this.locy = locy;
	}

	public Fridge getFridge() {
		return fridge;
	}

	public void setFridge(Fridge fridge) {
		this.fridge = fridge;
	}

	public Ingredientsdetail getIngredientsdetail() {
		return ingredientsdetail;
	}

	public void setIngredientsdetail(Ingredientsdetail ingredientsdetail) {
		this.ingredientsdetail = ingredientsdetail;
	}

	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", expired=" + expired + ", locx=" + locx + ", locy=" + locy + ", fridge="
				+ fridge + ", ingredientsdetail=" + ingredientsdetail + "]";
	}

}
