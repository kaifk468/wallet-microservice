package com.infy.userservice.model;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

	private Integer userId;
	private String emailId;
	private String mobileNumber;
	private String name;
	private String password;
	private UserStatusDto userStatusDto;
	private Double balance;
	private String successMessage;
	private String errorMessage;
	private Integer rewardPoints;
	private List<UserTransactionDto> userTransactionDtos;
	private SecurityQuestionDto securityQuestionDto;
	private String securityAnswer;
	private String newPassword;
	private String confirmNewPassword;



}
