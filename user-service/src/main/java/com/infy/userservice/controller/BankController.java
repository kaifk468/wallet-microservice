package com.infy.userservice.controller;

import com.infy.userservice.model.MoneyRequestDto;
import com.infy.userservice.model.UserTransactionDto;
import com.infy.userservice.service.UserTransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("bank")
public class BankController {
	

	@Autowired
	private Environment environment;

	
	@Autowired
	UserTransactionService userTransactionService;
	
	static Logger logger = LogManager.getLogger(BankController.class.getName());
	

   //Load money from bank to the wallet
	@RequestMapping(value="load-money", method = RequestMethod.POST)
	public ResponseEntity<UserTransactionDto> loadMoneyFromBank(@RequestBody MoneyRequestDto moneyRequestDto){

		logger.info("USER TRYING TO LOAD MONEY FROM BANK USIN ACCOUNT NUMBER, USER ID : "+ moneyRequestDto.getUserId());

		UserTransactionDto userTrasaction = userTransactionService.loadMoneyFromBank(moneyRequestDto);

		logger.info("MONEY LOADED FROM BANK USING DEBIT CARD, USER ID : "+ moneyRequestDto.getUserId());

		return new ResponseEntity<UserTransactionDto>(userTrasaction, HttpStatus.ACCEPTED);

	}


	//transfer money to bank account
	@RequestMapping(value = "send-money-bank", method = RequestMethod.POST)
	public ResponseEntity<UserTransactionDto> sendMoneyToBank(
			@RequestBody MoneyRequestDto moneyRequestDto) {

		logger.info("USER TRYING TO SEND MONEY TO BANK ACCOUNT, USER ID : "
				+ moneyRequestDto.getUserId());

		UserTransactionDto userTrasaction = userTransactionService.sendMoneyToBank(moneyRequestDto);

		logger.info("MONEY SENT TO BANK ACCOUNT, USER ID : " + moneyRequestDto.getUserId());

		return new ResponseEntity<UserTransactionDto>(userTrasaction,
				HttpStatus.ACCEPTED);

	}
	

}
