package com.amity.hms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAUTH")
public class AuthBean {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="email_id", nullable=false)
	private String emailId;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="user_type", nullable=false)
	private String userType;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
