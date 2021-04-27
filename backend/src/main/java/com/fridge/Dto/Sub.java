package com.fridge.Dto;

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
	int recipe_id;
	@ManyToOne
	private Ingredientsdetail ingredientsdetail;
	int ingredientsdetail_id;
	public Sub(int id, int recipe_id, int ingredientsdetail_id) {
		super();
		this.id = id;
		this.recipe_id = recipe_id;
		this.ingredientsdetail_id = ingredientsdetail_id;
	}
	public Sub() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	public int getIngredientsdetail_id() {
		return ingredientsdetail_id;
	}
	public void setIngredientsdetail_id(int ingredientsdetail_id) {
		this.ingredientsdetail_id = ingredientsdetail_id;
	}
	@Override
	public String toString() {
		return "Sub [id=" + id + ", recipe_id=" + recipe_id + ", ingredientsdetail_id=" + ingredientsdetail_id + "]";
	}
	
}
