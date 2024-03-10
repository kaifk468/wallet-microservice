package com.amigowallet.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.amigowallet.model.MerchantStatusDto;


@Entity
@Table(name="MERCHANT")
public class Merchant {
	
	@Id
	@Column(name="MERCHANT_ID")
	private Integer merchantId;
	@Column(name="EMAIL_ID")
	private String emailId;
	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;
	@Column(name="NAME")
	private String name;
	@Column(name="PASSWORD")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "MERCHANT_STATUS")
	private MerchantStatusDto merchantStatusDto;
	
	@CreationTimestamp
	@Column(name = "CREATED_TIMESTAMP")
	private LocalDateTime createdTimestamp;
	
	@UpdateTimestamp
	@Column(name = "MODIFIED_TIMESTAMP")
	private LocalDateTime modifiedTimestamp;

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MerchantStatusDto getMerchantStatus() {
		return merchantStatusDto;
	}

	public void setMerchantStatus(MerchantStatusDto merchantStatusDto) {
		this.merchantStatusDto = merchantStatusDto;
	}

	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public LocalDateTime getModifiedTimestamp() {
		return modifiedTimestamp;
	}

	public void setModifiedTimestamp(LocalDateTime modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}
	
	
}