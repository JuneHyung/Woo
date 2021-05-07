package com.fridge.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "포스트 글 정보", description = "포스트 글 상세 정보를 담는 Dto")
public class PostDto {
	@Schema(title = "글 고유 번호 - PK")
	private int id;
	@Schema(title = "글 제목")
	private String title;
	@Schema(title = "작성일")
	private String date;
	@Schema(title = "이미지 수")
	private int imageCnt;
	@Schema(title = "조회 수")
	private int visit;
	@Schema(title = "좋아요 수")
	private int good;
	@Schema(title = "싫어요 수")
	private int hate;
	@Schema(title = "작성자 이름")
	private String user_name;
	@Schema(title = "글에 포함되어 있는 이미지")
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
