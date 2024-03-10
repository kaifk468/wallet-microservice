package com.amigowallet.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.amigowallet.model.TransactionStatusDto;
@Entity
@Table(name="MERCHANT_TRANSACTION")
public class MerchantTransaction {
	
	@Id
	@Column(name="MERCHANT_TRANSACTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer merchantTransactionId;
	@Column(name="AMOUNT")
	private Double amount;
	@CreationTimestamp
	@Column(name="TRANSACTION_DATE_TIME")
	private LocalDateTime transactionDateTime;
	@Column(name="PAYMENT_TYPE_ID")
	private Integer paymentTypeId;
	@Column(name="REMARKS")
	private String remarks;
	@Column(name="INFO")
	private String info;
	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_STATUS")
	private TransactionStatusDto transactionStatusDto;
	@Column(name="MERCHANT_ID ")
	private Integer merchantId;
	public Integer getMerchantTransactionId() {
		return merchantTransactionId;
	}
	public void setMerchantTransactionId(Integer merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public TransactionStatusDto getTransactionStatus() {
		return transactionStatusDto;
	}
	public void setTransactionStatus(TransactionStatusDto transactionStatusDto) {
		this.transactionStatusDto = transactionStatusDto;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	
	




}
