package com.amigowallet.dao;

import java.util.Random;

import com.amigowallet.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import com.amigowallet.model.PaymentTypeDto;
import com.amigowallet.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amigowallet.model.UserTransactionDto;
import com.amigowallet.utility.AmigoWalletConstants;

@Repository("WalletToWalletDAO")
public class WalletToWalletDAOImpl implements WalletToWalletDAO{
	
	@Autowired
	UserLoginDAO userLoginDAO;

	
	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	UserService userService;
	
	@Override
	public Integer transferToWallet(Integer userId, Double amountToTransfer, String emailIdToTransfer) throws Exception{
		
		// TODO Auto-generated method stub
		
		UserDto receiver=userService.getUserByEmailFromUserService(emailIdToTransfer);//userLoginDAO.getUserByEmailId(emailIdToTransfer);
		UserDto sender=userService.getUserByUserIdFromUserService(userId);//userLoginDAO.getUserByUserId(userId);
		Double number=0.0;
		if(sender==receiver) {
			throw new Exception("WalletService.PAYING_HIMSELF");
		}
		if(sender==null){
			return 0;
		}
		if(receiver==null){
			throw new Exception("WalletToWalletService.INVALID_EMAIL");
		}
		if(amountToTransfer>=200){
			number=amountToTransfer*0.02;
		}
		Random random=new Random();

		Integer rewardpoint=random.nextInt(5);

		UserTransactionDto userTransactionDtoSender = new UserTransactionDto();
		
		/*
		 * A new userTransaction is created here and all the properties are populated
		 */
		userTransactionDtoSender.setAmount(amountToTransfer);
		userTransactionDtoSender.setInfo(AmigoWalletConstants.TRANSACTION_INFO_MONEY_WALLET_TO_WALLET_DEBIT+" "+emailIdToTransfer);
		userTransactionDtoSender.setPointsEarned(rewardpoint);
		userTransactionDtoSender.setIsRedeemed(AmigoWalletConstants.REWARD_POINTS_REDEEMED_NO.charAt(0));
		userTransactionDtoSender.setRemarks("D");
		
		
		UserTransactionDto userTransactionDtoReceiver = new UserTransactionDto();
		userTransactionDtoReceiver.setAmount(amountToTransfer);
		userTransactionDtoReceiver.setInfo(AmigoWalletConstants.TRANSACTION_INFO_MONEY_WALLET_TO_WALLET_CREDIT+" "+sender.getEmailId());
		userTransactionDtoReceiver.setPointsEarned(0);
		userTransactionDtoReceiver.setIsRedeemed(AmigoWalletConstants.REWARD_POINTS_REDEEMED_NO.charAt(0));
		userTransactionDtoReceiver.setRemarks("C");
		
		if(walletDebit(userTransactionDtoSender, userId)==0 || walletCredit(userTransactionDtoReceiver, receiver.getUserId())==0){
			return 0;
		}
		
		if(number!=0.0){
		UserTransactionDto userTransactionDtoSender1 = new UserTransactionDto();
		
		/*
		 * A new userTransaction is created here for cashback and all the properties are populated
		 */
		userTransactionDtoSender1.setAmount(number);
		userTransactionDtoSender1.setInfo(AmigoWalletConstants.TRANSACTION_INFO_MONEY_CASHBACK_TO_WALLET_CREDIT+" "+number);
		userTransactionDtoSender1.setPointsEarned(0);
		userTransactionDtoSender1.setIsRedeemed(AmigoWalletConstants.REWARD_POINTS_REDEEMED_NO.charAt(0));
		userTransactionDtoSender1.setRemarks("C");
		
		walletCredit(userTransactionDtoSender1, userId);
		}
		return 1;
	}
	/**
	 * This method is used to Debit money for given user transaction with userID passed as parameter.
	 * 
	 * @param userTransactionDto,userID
	 * 
	 * @return Integer 
	 */
	public Integer walletDebit(UserTransactionDto userTransactionDto, Integer userId) {


		//todo start
		PaymentTypeDto paymentTypeDto=new PaymentTypeDto();
		paymentTypeDto.setPaymentTypeId(2);
		userTransactionDto.setPaymentType(paymentTypeDto);

        return userService.addTransactionToUserService(userTransactionDto,userId);

		//todo end
	}

	/**
	 * This method is used to Credit money for given user transaction with userID passed as parameter.
	 * 
	 * @param userTransactionDto,userID
	 * 
	 * @return Integer 
	 */
	public Integer walletCredit(UserTransactionDto userTransactionDto, Integer userId) {

		 //todo start

		PaymentTypeDto paymentTypeDto=new PaymentTypeDto();
		paymentTypeDto.setPaymentTypeId(1);
		userTransactionDto.setPaymentType(paymentTypeDto);

		userTransactionDto.setPaymentType(paymentTypeDto);
		return userService.addTransactionToUserService(userTransactionDto,userId);
		}
}
