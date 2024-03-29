package com.amigowallet.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class UserTransactionDto {

	private Long userTransactionId;
	private Double amount;
	@JsonFormat(pattern="yyyy-MMM-dd hh:mm:ss a")
	private LocalDateTime transactionDateTime;
	private String remarks;
	private String info;
	private Integer pointsEarned;
	private Character isRedeemed;
	private String message;
	private PaymentTypeDto paymentTypeDto;
	private TransactionStatusDto transactionStatusDto;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PaymentTypeDto getPaymentType() {
		return paymentTypeDto;
	}

	public void setPaymentType(PaymentTypeDto paymentTypeDto) {
		this.paymentTypeDto = paymentTypeDto;
	}

	public TransactionStatusDto getTransactionStatus() {
		return transactionStatusDto;
	}

	public void setTransactionStatus(TransactionStatusDto transactionStatusDto) {
		this.transactionStatusDto = transactionStatusDto;
	}

	public Long getUserTransactionId() {
		return userTransactionId;
	}

	public void setUserTransactionId(Long userTransactionId) {
		this.userTransactionId = userTransactionId;
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

	public Integer getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public Character getIsRedeemed() {
		return isRedeemed;
	}

	public void setIsRedeemed(Character isRedeemed) {
		this.isRedeemed = isRedeemed;
	}

}
