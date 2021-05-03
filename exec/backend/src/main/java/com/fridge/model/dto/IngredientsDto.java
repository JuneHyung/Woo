package com.fridge.model.dto;

public class IngredientsDto {
	private String expired;
	private int locx;
	private int locy;
	private int fridgeId;
	private int ingredientsDetailId;

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

}
