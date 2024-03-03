package com.infy.userservice.service;


import com.infy.userservice.model.UserDto;

public interface ForgotPasswordService
{

	public UserDto authenticateEmailId(String emailId) throws Exception;

	public void validateSecurityAnswer(UserDto userDto) throws Exception;

	public void resetPassword(UserDto userDto) throws Exception;
}
