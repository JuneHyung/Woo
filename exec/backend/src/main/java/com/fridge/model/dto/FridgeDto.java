package com.fridge.model.dto;

public class FridgeDto {
	private String name;
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
