package com.infy.userservice.service;

import com.infy.userservice.model.MoneyRequestDto;
import com.infy.userservice.model.UserTransactionDto;

public interface UserTransactionService {


	UserTransactionDto loadMoneyFromBank(MoneyRequestDto moneyRequestDto);


	UserTransactionDto sendMoneyToBank(MoneyRequestDto moneyRequestDto);
}
