package com.fridge.model.dto;

import java.util.Arrays;

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
	@Schema(title = "작성자 고유 번호 - FK")
	private int userId;
	@Schema(title = "작성자 이름")
	private String writer;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String[] getImageStrArr() {
		return imageStrArr;
	}

	public void setImageStrArr(String[] imageStrArr) {
		this.imageStrArr = imageStrArr;
	}

	@Override
	public String toString() {
		return "PostDto [id=" + id + ", title=" + title + ", date=" + date + ", imageCnt=" + imageCnt + ", visit="
				+ visit + ", good=" + good + ", hate=" + hate + ", userId=" + userId + ", writer=" + writer
				+ ", imageStrArr=" + Arrays.toString(imageStrArr) + "]";
	}

}
