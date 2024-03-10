package com.infy.userservice.repo;


import com.infy.userservice.model.MoneyRequestDto;
import com.infy.userservice.model.UserTransactionDto;

/**
 * This is a DAO interface having method to perform CRUD operations on user and  transactions
 * on performing different transactions like loadMoney, debitMoney etc.
 * 
 * @author ETA_JAVA
 *
 */
public interface UserTransactionDAO {


	UserTransactionDto loadMoneyToWallet(UserTransactionDto userTransactionDto, MoneyRequestDto moneyRequestDto);
	

	UserTransactionDto sendMoneyToBank(UserTransactionDto userTransactionDto, MoneyRequestDto moneyRequestDto);

}
