package com.infy.userservice.service;

import com.infy.userservice.model.SecurityQuestionDto;
import com.infy.userservice.model.UserDto;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public interface RegistrationService {
	

	public void validateUser(UserDto userDto) throws Exception;

	public Integer registerUser(UserDto userDto) throws NoSuchAlgorithmException;

	public ArrayList<SecurityQuestionDto> getAllSecurityQuestions();
	
}
