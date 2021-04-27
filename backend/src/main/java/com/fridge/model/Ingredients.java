package com.fridge.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Table(name ="ingredients")
@Entity
public class Ingredients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	LocalDateTime expired;
	int locx;
	int locy;
	@ManyToOne
	private Fridge fridge;
	@ManyToOne
	private Ingredientsdetail ingredientsdetail;
	public Ingredients(int id, LocalDateTime expired, int locx, int locy, Fridge fridge,
			Ingredientsdetail ingredientsdetail) {
		super();
		this.id = id;
		this.expired = expired;
		this.locx = locx;
		this.locy = locy;
		this.fridge = fridge;
		this.ingredientsdetail = ingredientsdetail;
	}
	public Ingredients() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getExpired() {
		return expired;
	}
	public void setExpired(LocalDateTime expired) {
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
