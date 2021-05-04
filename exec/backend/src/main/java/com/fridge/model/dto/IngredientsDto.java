package com.fridge.model.dto;

public class IngredientsDto {
	private int id;
	private String expired;
	private int locx;
	private int locy;
	private int fridgeId;
	private int ingredientsDetailId;

	public IngredientsDto(int id, String expired, int locx, int locy, int fridgeId, int ingredientsDetailId) {
		super();
		this.id = id;
		this.expired = expired;
		this.locx = locx;
		this.locy = locy;
		this.fridgeId = fridgeId;
		this.ingredientsDetailId = ingredientsDetailId;
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



	public int getFridgeId() {
		return fridgeId;
	}



	public void setFridgeId(int fridgeId) {
		this.fridgeId = fridgeId;
	}



	public int getIngredientsDetailId() {
		return ingredientsDetailId;
	}


	public void setIngredientsDetailId(int ingredientsDetailId) {
		this.ingredientsDetailId = ingredientsDetailId;
	}



	@Override
	public String toString() {
		return "IngredientsDto [id=" + id + ", expired=" + expired + ", locx=" + locx + ", locy=" + locy + ", fridgeId="
				+ fridgeId + ", ingredientsDetailId=" + ingredientsDetailId + "]";
	}


}
