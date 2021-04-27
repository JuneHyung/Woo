package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String category;
	String subcategory;
	String url;
	int views;
	public Recipe(int id, String name, String category, String subcategory, String url, int views) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.subcategory = subcategory;
		this.url = url;
		this.views = views;
	}
	public Recipe() {
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
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", category=" + category + ", subcategory=" + subcategory
				+ ", url=" + url + ", views=" + views + "]";
	}
	
}
