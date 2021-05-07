package com.fridge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "recipe")
@Schema(name = "Recipe Table", description = "레시피 정보 저장 Entity")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "user pk")
	int id;
	@Schema(title = "레시피 이름")
	String name;
	@Schema(title = "레시피 메인 카테고리")
	String category;
	@Schema(title = "레시피 서브 카테고리")
	String subcategory;
	@Schema(title = "레시피 주소")
	String url;
	@Schema(title = "레시피 조회수")
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
	
	public Recipe(Recipe r, int views) {
		this.id = r.getId();
		this.name = r.getName();
		this.category = r.getCategory();
		this.subcategory = r.getSubcategory();
		this.url = r.getUrl();
		this.views = views;
	}

	public Recipe() {
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

	public int getViews() {
		return views;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", category=" + category + ", subcategory=" + subcategory
				+ ", url=" + url + ", views=" + views + "]";
	}

}
