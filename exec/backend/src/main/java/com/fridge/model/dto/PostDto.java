package com.fridge.model.dto;

public class PostDto {
	private int id;
	private String title;
	private String date;
	private int imageCnt;
	private int visit;
	private int good;
	private int hate;
	private String user_name;
	private String[] imageStrArr;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getImageCnt() {
		return imageCnt;
	}

	public void setImageCnt(int imageCnt) {
		this.imageCnt = imageCnt;
	}

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String[] getImageStrArr() {
		return imageStrArr;
	}
	
	public void setImageStrArr(String[] imageStrArr) {
		this.imageStrArr = imageStrArr;
	}

	@Override
	public String toString() {
		return "PostDto [title=" + title + ", date=" + date + ", imageCnt=" + imageCnt + ", visit=" + visit + ", good="
				+ good + ", hate=" + hate + ", user_name=" + user_name + "]";
	}

}
