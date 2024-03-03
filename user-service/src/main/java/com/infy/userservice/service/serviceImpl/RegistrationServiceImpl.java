package com.infy.userservice.service.serviceImpl;


import com.infy.userservice.model.SecurityQuestionDto;
import com.infy.userservice.model.UserDto;
import com.infy.userservice.repo.RegistrationDAO;
import com.infy.userservice.service.RegistrationService;
import com.infy.userservice.util.HashingUtility;
import com.infy.userservice.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service(value = "registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationDAO registrationDao;

	@Override
	public void validateUser(UserDto userDto) throws Exception {

		RegistrationValidator.validateUserDetails(userDto);

		Boolean emailAvailable = registrationDao.checkEmailAvailability(userDto
				.getEmailId());


		if (emailAvailable) {
			throw new Exception(
					"RegistrationService.EMAIL_ALREADY_PRESENT");
		}

		Boolean mobileNumberAvailable = registrationDao
				.checkMobileNumberAvailability(userDto.getMobileNumber());
		if (mobileNumberAvailable) {
			throw new Exception(
					"RegistrationService.MOBILE_NUMBER_ALREADY_PRESENT");
		}
	}

	@Override
	public Integer registerUser(UserDto userDto) throws NoSuchAlgorithmException {

		userDto.setPassword(HashingUtility.getHashValue(userDto.getPassword()));
		
		userDto.setEmailId(userDto.getEmailId().toLowerCase());

		Integer userId = registrationDao.addNewUser(userDto);
		
		return userId;
	}


	@Override
	public ArrayList<SecurityQuestionDto> getAllSecurityQuestions(){

		ArrayList<SecurityQuestionDto> securityQuestionDtos = registrationDao.getAllSecurityQuestions();
		
		return securityQuestionDtos;
	}

}
