package com.infy.userservice.service.serviceImpl;


import com.infy.userservice.model.MoneyRequestDto;
import com.infy.userservice.model.UserTransactionDto;
import com.infy.userservice.repo.UserTransactionDAO;
import com.infy.userservice.service.UserTransactionService;
import com.infy.userservice.util.AmigoWalletConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userTransactionService")
@Transactional
public class UserTransactionServiceImpl implements UserTransactionService {

	@Autowired
	private UserTransactionDAO userTransactionDAO;
	

	public UserTransactionDto loadMoneyFromBank(MoneyRequestDto moneyRequestDto)
	{

		UserTransactionDto userTransactionDto = new UserTransactionDto();

		userTransactionDto.setAmount(moneyRequestDto.getAmount());
		userTransactionDto.setInfo(AmigoWalletConstants.TRANSACTION_INFO_MONEY_ADDED_FROM_BANK_TO_EWALLET_USING_DEBIT_CARD);
		userTransactionDto.setPointsEarned(0);
		userTransactionDto.setIsRedeemed(AmigoWalletConstants.REWARD_POINTS_REDEEMED_YES.charAt(0));
		userTransactionDto = userTransactionDAO.loadMoneyToWallet(userTransactionDto, moneyRequestDto);

		return userTransactionDto;
	}


	

	@Override
	public UserTransactionDto sendMoneyToBank(MoneyRequestDto moneyRequestDto) {
		UserTransactionDto userTransactionDto = new UserTransactionDto();

		userTransactionDto.setAmount(moneyRequestDto.getAmount());
		userTransactionDto.setInfo(AmigoWalletConstants.TRANSACTION_INFO_MONEY_SENT_TO_BANK_ACCOUNT_FROM_EWALLET);
		userTransactionDto.setPointsEarned(0);
		userTransactionDto.setIsRedeemed(AmigoWalletConstants.REWARD_POINTS_REDEEMED_YES.charAt(0));

		userTransactionDto = userTransactionDAO.sendMoneyToBank(userTransactionDto, moneyRequestDto);

		return userTransactionDto;
	}
}
