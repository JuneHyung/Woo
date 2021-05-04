package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ingredientsdetail")
@Entity
public class Ingredientsdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String category;
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
