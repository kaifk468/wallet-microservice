package com.infy.userservice.service;

import com.infy.userservice.model.UserTransactionDto;

import java.util.List;

public interface TransactionHistoryService {

    public List<UserTransactionDto> getAllTransactionByUserId(Integer userId) throws Exception;
}
