package com.infy.userservice.service.serviceImpl;


import com.infy.userservice.model.UserDto;
import com.infy.userservice.repo.ForgotPasswordDAO;
import com.infy.userservice.service.ForgotPasswordService;
import com.infy.userservice.util.HashingUtility;
import com.infy.userservice.validator.UserLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "forgotPasswordService")
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	@Autowired
	private ForgotPasswordDAO forgotPasswordDAO;

	@Override
	public UserDto authenticateEmailId(String emailId)
			throws Exception {

		UserDto userDto = forgotPasswordDAO.authenticateEmailId(emailId);
		if (userDto == null) {
			throw new Exception(
					"ForgotPasswordService.INVALID_EMAIL");
		}
		return userDto;
	}

	@Override
	public void validateSecurityAnswer(UserDto userDto) throws Exception {
		
		UserDto userDtoFromDAO = forgotPasswordDAO.validateSecurityAnswer(userDto);

		
		if(!userDtoFromDAO.getSecurityAnswer().equalsIgnoreCase(userDto.getSecurityAnswer())) {
			throw new Exception("ForgotPasswordService.INVALID_SECURITY_ANSWER");
		}
	}

	@Override
	public void resetPassword(UserDto userDto) throws Exception {
		UserLoginValidator.validateResetPasswordDetails(userDto.getPassword());
		forgotPasswordDAO.resetPassword(userDto.getUserId(),
				HashingUtility.getHashValue(userDto.getPassword()));

		
	}
}
