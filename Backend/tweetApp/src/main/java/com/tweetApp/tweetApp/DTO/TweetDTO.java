package com.tweetApp.tweetApp.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class TweetDTO {
	
	private int id;
	
	private String userName;
	
	private String email;
	
	private String tweetDes;
	
	private String tweetTag;
	
	private String tweetDate;

	private String recActive;
	
	private int like;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTweetDes() {
		return tweetDes;
	}

	public void setTweetDes(String tweetDes) {
		this.tweetDes = tweetDes;
	}

	public String getTweetTag() {
		return tweetTag;
	}

	public void setTweetTag(String tweetTag) {
		this.tweetTag = tweetTag;
	}

	public String getTweetDate() {
		return tweetDate;
	}

	public void setTweetDate(String tweetDate) {
		this.tweetDate = tweetDate;
	}

	public String getRecActive() {
		return recActive;
	}

	public void setRecActive(String recActive) {
		this.recActive = recActive;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}
	
	
}
