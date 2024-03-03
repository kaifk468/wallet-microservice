package com.infy.userservice.entity;

import com.infy.userservice.model.UserStatusDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "WALLET_USER")
public class User {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PASSWORD")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_STATUS")
	private UserStatusDto userStatusDto;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SECURITY_QUESTION_ID")
	private SecurityQuestion securityQuestion;
	
	@Column(name="SECURITY_ANSWER")
	private String securityAnswer;
	
	@CreationTimestamp
	@Column(name = "CREATED_TIMESTAMP")
	private LocalDateTime createdTimestamp;
	@UpdateTimestamp
	@Column(name = "MODIFIED_TIMESTAMP")
	private LocalDateTime modifiedTimestamp;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<UserTransactionEntity> userTransactionEntities;

}
