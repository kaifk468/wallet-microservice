package com.infy.userservice.service;


import com.infy.userservice.model.UserDto;


public interface UserLoginService {
	

	public UserDto authenticate(UserDto userDto) throws Exception;

	public void changeUserPassword(UserDto userDto)
			throws Exception;


	public UserDto getUserbyUserId(Integer userId)
			throws Exception;

	UserDto getUserByEmail(String email) throws Exception;
}
