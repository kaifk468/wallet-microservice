package com.amigowallet.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This is an entity class mapped to DataBase table <q>RESET_PASSWORD</q>.
 * 
 * @author ETA_JAVA
 */

@Entity
@Table(name="RESET_PASSWORD")
public class ResetPassword {

	@Id
	@Column(name="TOKEN_ID")
	private Integer tokenId;
	@Column(name="EMAIL_ID")
	private String emailId;
	@Column(name="TOKEN_EXPIRY")
	private LocalDateTime tokenExpiry;
	
	public Integer getTokenId() {
		return tokenId;
	}
	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LocalDateTime getTokenExpiry() {
		return tokenExpiry;
	}
	public void setTokenExpiry(LocalDateTime tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}	
}
