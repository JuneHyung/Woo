package com.fridge.model.dto;

public class RecipeDto {
	int id;
	String name;
	String category;
	String subcategory;
	String url;
	int view;
	public RecipeDto(int id, String name, String category, String subcategory, String url, int view) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.subcategory = subcategory;
		this.url = url;
		this.view = view;
	}
	public RecipeDto() {
		super();
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
	public String getSubcategory() {
		return subcategory;
	}
	public String getUrl() {
		return url;
	}
	public int getView() {
		return view;
	}
	@Override
	public String toString() {
		return "RecipeDto [id=" + id + ", name=" + name + ", category=" + category + ", subcategory=" + subcategory
				+ ", url=" + url + ", view=" + view + "]";
	}
	
}
