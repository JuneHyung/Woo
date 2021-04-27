package com.fridge.Dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "main")
public class Main {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	int recipe_id;
	@ManyToOne
	private Ingredientsdetail ingredientsdetail;
	public Main(int id, int recipe_id, Ingredientsdetail ingredientsdetail) {
		super();
		this.id = id;
		this.recipe_id = recipe_id;
		this.ingredientsdetail = ingredientsdetail;
	}
	public Main() {
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
	public Ingredientsdetail getIngredientsdetail() {
		return ingredientsdetail;
	}
	public void setIngredientsdetail(Ingredientsdetail ingredientsdetail) {
		this.ingredientsdetail = ingredientsdetail;
	}
	@Override
	public String toString() {
		return "Main [id=" + id + ", recipe_id=" + recipe_id + ", ingredientsdetail=" + ingredientsdetail + "]";
	}
	
}
