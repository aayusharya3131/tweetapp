package com.tweetApp.tweetApp.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "reply")
@Data
@Component
@Builder
public class Reply {
	
	@DynamoDBHashKey(attributeName="userName")	
	private String userName;
	
	@DynamoDBAttribute
	private String replyDesc;
	
	@DynamoDBAttribute
	private String date;

	
	  public Reply() { 
		  super(); 
		  }
	  
	  public Reply(String userName, String replyDesc, String date) {
	  super(); this.userName = userName; this.replyDesc = replyDesc; this.date =
	  date; }
	 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReplyDesc() {
		return replyDesc;
	}

	public void setReplyDesc(String replyDesc) {
		this.replyDesc = replyDesc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
