package com.tweetApp.tweetApp.model;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "users")
@Data
@Component
@Builder
public class Register {
	
	@DynamoDBHashKey(attributeName="loginId")
	@DynamoDBAutoGeneratedKey
	private String id;
	//private String loginId=id;
	
	@DynamoDBAttribute
	private String firstName;
	
	@DynamoDBAttribute
	private String lastName;
	
	@DynamoDBAttribute
	private String email;
	
	@DynamoDBAttribute
	private String userName;
	
	@DynamoDBAttribute
	private String password;
	
	@DynamoDBAttribute
	private long contactNum;

	
	
	
	  public Register() {
		  super(); // TODO Auto-generated constructor stub }
	  }
	 

	
	  public Register(String id, String firstName, String lastName, String email,
	  String userName, String password, long contactNum) { 
		  super();
		  this.id = id;
	  this.firstName = firstName;
	  this.lastName = lastName; 
	  this.email = email;
	  this.userName = userName;
	  this.password = password; 
	  this.contactNum = contactNum; }
	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getContactNum() {
		return contactNum;
	}

	public void setContactNum(long contactNum) {
		this.contactNum = contactNum;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userName=" + userName + ", password=" + password + ", contactNum=" + contactNum + "]";
	}
	
	

}
