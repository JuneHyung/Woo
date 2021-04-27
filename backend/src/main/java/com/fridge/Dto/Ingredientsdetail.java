package com.fridge.Dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name ="ingredientsdetail")
@Entity
public class Ingredientsdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String category;
	String image;
	LocalDate avgdate;
	public Ingredientsdetail(int id, String name, String category, String image, LocalDate avgdate) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.image = image;
		this.avgdate = avgdate;
	}
	public Ingredientsdetail() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public LocalDate getAvgdate() {
		return avgdate;
	}
	public void setAvgdate(LocalDate avgdate) {
		this.avgdate = avgdate;
	}
	@Override
	public String toString() {
		return "Ingredientsdetail [id=" + id + ", name=" + name + ", category=" + category + ", image=" + image
				+ ", avgdate=" + avgdate + "]";
	}
	
}
