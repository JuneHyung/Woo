package com.fridge.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "냉장고 정보 Dto", description = "냉장고 이름, type 만을 담는 Dto")
public class FridgeDto {
	@Schema(title = "냉장고 이름")
	private String name;
	@Schema(title = "냉장고 타입")
	int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FridgeDto [name=" + name + ", type=" + type + "]";
	}

}
