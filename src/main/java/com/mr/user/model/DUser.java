package com.mr.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "d_user", catalog = "dictionarydb")
public class DUser implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	//role and access level
	private Integer access;
	//Just a password for confirmation
	@Transient
	private String userConfirmPassword;
	
	public DUser() {
	}
	
	public DUser(String userName, String userPassword, String userEmail, int access) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.access = access;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "user_password", nullable = false)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Column(name = "user_email", nullable = false)
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Column(name = "user_access", nullable = false)
	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}
	
	@Transient
	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}

	public void setUserConfirmPassword(String userConfirmPassword) {
		this.userConfirmPassword = userConfirmPassword;
	}
	
	@Override
	public String toString() {
		return "DUser [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userEmail=" + userEmail
				+ ", access=" + access + "]";
	}	
}
