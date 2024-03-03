package com.infy.userservice.service.serviceImpl;

import com.infy.userservice.model.UserTransactionDto;
import com.infy.userservice.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a service class having method which contains business logic
 * related to fetching transaction history.
 * 
 * @author KARAN RAJ SINGH
 *
 */
@Service(value = "transactionHistoryService")
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

//	@Autowired
//	private RewardPointsDAO rewardPointsDAO;

	@Override
	public List<UserTransactionDto> getAllTransactionByUserId(Integer userId) throws Exception {
		
		/*
		 * here we are passing user id as an argument in
		 * getAllTransactionByUserId method of rewardPointsDAO and gets the user
		 * transaction details from the data base table and then returns user
		 * transaction list model object If transaction is not found in the data
		 * base then it returns null
		 */
		//Todo Need to do this through restTEmplate
		List<UserTransactionDto> userTransactionDtos =new ArrayList<>();//= rewardPointsDAO.getAllTransactionByUserId(userId);
		
		/*
		 * if there is no transaction data in database then exception is thrown with
		 * message TransactionHistoryService.NO_TRANSACTIONS_FOUND
		 */
//		if (userTransactionDtos ==null)
//			throw new Exception("TransactionHistoryService.NO_TRANSACTIONS_FOUND");
		
		return userTransactionDtos;
	}
}
