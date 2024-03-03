package com.infy.userservice.entity;


import com.infy.userservice.model.TransactionStatusDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="USER_TRANSACTION")
@Data
public class UserTransactionEntity {

	@Id
	@Column(name="USER_TRANSACTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userTransactionId;
	@Column(name = "AMOUNT")
	private Double amount;
	@CreationTimestamp
	@Column(name="TRANSACTION_DATE_TIME")
	private LocalDateTime transactionDateTime;
	@Column(name = "REMARKS")
	private String remarks;
	@Column(name = "INFO")
	private String info;
	@Column(name="POINTS_EARNED")
	private Integer pointsEarned;
	@Column(name="IS_REDEEMED")
	private Character isRedeemed;

	@ManyToOne
	@JoinColumn(name = "PAYMENT_TYPE_ID")
	private PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_STATUS")
	private TransactionStatusDto transactionStatusDto;


	
}
