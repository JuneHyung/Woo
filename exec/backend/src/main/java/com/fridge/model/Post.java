package com.fridge.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Table(name = "post")
@Entity
@Schema(description = "포스트 저장을 위한 Entity")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(title = "게시글 번호 (PK)")
	@Column(nullable = false)
	int id;
	@Schema(title = "게시글 제목")
	@Column(nullable = false,length = 45)
	String title;
	@Schema(title = "게시글 내용")
	@Column(nullable = true , length = 256)
	String contents;
	@Schema(title = "게시글 작성일")
	@Column(nullable = true)
	LocalDateTime date;
	@Schema(title = "게시글 이미지 개수")
	@Column(nullable = true)
	int imagecnt;
	@Column(nullable = true)
	@Schema(title = "게시글 조회수")
	int visit;
	@Schema(title = "게시글 좋아요 수")
	@Column(nullable = true)
	int good;
	@Schema(title = "개시글 싫어요 수")
	@Column(nullable = true)
	int hate;
	@Schema(title = "게시글 작성자(nickname)")
	@Column(nullable = false , length = 45)
	String user_name;
	@ManyToOne
	@Schema(title = "user table과의 FK")
	@JoinColumn(nullable = false , name = "user_id")
	private User user;

	protected Post() {
		super();
	}

	public Post(int id) {
		this.id = id;
	}

	public Post(Post post, int visit) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.contents = post.getContents();
		this.date = post.getDate();
		this.imagecnt = post.getImagecnt();

		this.visit = visit;

		this.good = post.getGood();
		this.hate = post.getHate();
		this.user_name = post.getUser_name();
		this.user = post.getUser();

	}

	public Post(Post post, int good, int hate) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.contents = post.getContents();
		this.date = post.getDate();
		this.imagecnt = post.getImagecnt();
		this.visit = post.getVisit();
		this.user_name = post.getUser_name();
		this.user = post.getUser();
		this.good = good;
		this.hate = hate;
	}

	public Post(String title, int imageCnt, String user_name, User user) {
		this.title = title;
		this.contents = "";
		this.date = LocalDateTime.now();
		this.imagecnt = imageCnt;
		this.user_name = user_name;
		this.user = user;
	}

	public Post(int id, String title, int imageCnt, String user_name, User user) {
		this.id = id;
		this.title = title;
		this.contents = "";
		this.date = LocalDateTime.now();
		this.imagecnt = imageCnt;
		this.user_name = user_name;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getImagecnt() {
		return imagecnt;
	}

	public int getVisit() {
		return visit;
	}

	public int getGood() {
		return good;
	}

	public int getHate() {
		return hate;
	}

	public String getUser_name() {
		return user_name;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", contents=" + contents + ", date=" + date + ", imagecnt="
				+ imagecnt + ", visit=" + visit + ", good=" + good + ", hate=" + hate + ", user_name=" + user_name
				+ ", user=" + user + "]";
	}

}
