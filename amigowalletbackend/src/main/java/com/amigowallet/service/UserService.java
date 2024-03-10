package com.amigowallet.service;

import com.amigowallet.model.UserDto;
import com.amigowallet.model.UserTransactionDto;

public interface UserService {


    public UserDto getUserByEmailFromUserService(String email);

    public UserDto getUserByUserIdFromUserService(Integer userId);

    public Integer addTransactionToUserService(UserTransactionDto userTransactionDto, Integer userId);

}
