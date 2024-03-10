package com.infy.userservice.service;

import com.infy.userservice.model.UserTransactionDto;

public interface UserService {


    void addAndUpdateUserTransaction(Integer userId,UserTransactionDto userTransactionDto);

    void addTransaction(Integer userId,UserTransactionDto userTransactionDto);
}
