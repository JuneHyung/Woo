package com.fridge.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "재료 정보 담는 Dto", description = "id, 유통기한, x, y 좌표, 냉장고 ID, 재료 상세 ID를 포함하는 객체")
public class IngredientsDto {
	@Schema(title = "고유 ID - PK")
	private int id;
	@Schema(title = "유통기한")
	private String expired;
	@Schema(title = "위치 X 좌표")
	private int locx;
	@Schema(title = "위치 Y 좌표")
	private int locy;
	@Schema(title = "냉장고 고유 ID - FK")
	private int fridgeId;
	@Schema(title = "재료 정보 ID - FK")
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
