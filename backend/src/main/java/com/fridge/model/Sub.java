package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="sub")
public class Sub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	private Recipe recipe;
	@ManyToOne
	private Ingredientsdetail ingredientsdetail;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredientsdetail getIngredientsdetail() {
		return ingredientsdetail;
	}

	public void setIngredientsdetail(Ingredientsdetail ingredientsdetail) {
		this.ingredientsdetail = ingredientsdetail;
	}

	@Override
	public String toString() {
		return "Sub [id=" + id + ", recipe=" + recipe + ", ingredientsdetail=" + ingredientsdetail + "]";
	}
}