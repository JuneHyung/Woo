package com.fridge.Dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name ="post")
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String title;
	String contents;
	LocalDateTime date;
	int imagecnt;
	int visit;
	int good;
	int hate;
	String user_name;
	@ManyToOne
	private User user;
	public Post(int id, String title, String contents, LocalDateTime date, int imagecnt, int visit, int good, int hate,
			String user_name, User user) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.imagecnt = imagecnt;
		this.visit = visit;
		this.good = good;
		this.hate = hate;
		this.user_name = user_name;
		this.user = user;
	}
	public Post() {
		super();
	}
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getImagecnt() {
		return imagecnt;
	}
	public void setImagecnt(int imagecnt) {
		this.imagecnt = imagecnt;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", contents=" + contents + ", date=" + date + ", imagecnt="
				+ imagecnt + ", visit=" + visit + ", good=" + good + ", hate=" + hate + ", user_name=" + user_name
				+ ", user=" + user + "]";
	}
	
}
