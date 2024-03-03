package com.amigowallet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amigowallet.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import jakarta.ws.rs.core.UriBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.amigowallet.entity.PaymentType;
import com.amigowallet.entity.User;
import com.amigowallet.entity.UserTransactionEntity;
import com.amigowallet.utility.AmigoWalletConstants;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This is a DAO class having methods to perform CRUD operations related to reward points.
 *
 * @author ETA_JAVA
 * 
 */
@Repository(value = "rewardPointsDAO")
public class RewardPointsDAOImpl implements RewardPointsDAO {
	
	/** This is a spring auto-wired attribute used to create data base sessions */
	@Autowired
	EntityManager entityManager;

	@Autowired
	RestTemplate restTemplate;


	@Value("${com.infy.user.service.url}")
	private String userServiceUrl;
	
	/**
	 * This method receives user id as argument and gets the transaction
	 * details for that user
	 * It returns user transactions list
	 * If no transaction is not found in the data base then it returns
	 * null
	 * 
	 * @param userId
	 * 
	 * @return list of user transaction
	 */
	@Override
	public List<UserTransactionDto> getAllTransactionByUserId(Integer userId) {
		
		/*
		 * All the userTransaction entities for the user is fetched 
		 */
		//todo We have to communicate with User-service to get Trax of User
		UserDto userDto=new UserDto();
		userDto.setUserId(1);

		RequestEntity requestEntity = RequestEntity
				.post(UriComponentsBuilder.fromUriString(userServiceUrl+"/user-login/user").build().toUri())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(userDto);
		ResponseEntity<UserDto> responseEntity = restTemplate.exchange(requestEntity,
				new ParameterizedTypeReference<UserDto>() {});
		if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null && responseEntity.getBody().getUserTransactionDtos()!=null) {
			return responseEntity.getBody().getUserTransactionDtos();
		}
		else {
			throw new RuntimeException("User Or tranx not found");
		}

//		//todo end here
//		User user = entityManager.find(User.class, userId);
//		List<UserTransactionEntity> transactionEntities = user.getUserTransactionEntities();
//		List<UserTransactionDto> userTransactionDtos = null;
//
//		/*
//		 * For each userTransactionEntity in the list of transactions
//		 * a user transaction bean is populated with the corresponding properties
//		 * of the userTransaction Entity
//		 */
//		if (transactionEntities != null && !transactionEntities.isEmpty()) {
//			userTransactionDtos = new ArrayList<UserTransactionDto>();
//			for (UserTransactionEntity userTransactionEntity : transactionEntities) {
//
//				UserTransactionDto userTransactionDto = new UserTransactionDto();
//				userTransactionDto.setAmount(userTransactionEntity.getAmount());
//				userTransactionDto.setInfo(userTransactionEntity.getInfo());
//				userTransactionDto.setIsRedeemed(userTransactionEntity.getIsRedeemed());
//
//				/*
//				 * The properties of a  paymentType bean is populated by
//				 * getting the  corresponding properties of paymentTypeEntity of
//				 * userTransaction Entity
//				 */
//				PaymentType paymentTypeEntity = userTransactionEntity.getPaymentTypeEntity();
//				if (paymentTypeEntity != null) {
//
//					PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
//					paymentTypeDto.setPaymentFrom(paymentTypeEntity.getPaymentFrom());
//					paymentTypeDto.setPaymentTo(paymentTypeEntity.getPaymentTo());
//					paymentTypeDto.setPaymentType(paymentTypeEntity.getPaymentType());
//					paymentTypeDto.setPaymentTypeId(paymentTypeEntity.getPaymentTypeId());
//					userTransactionDto.setPaymentType(paymentTypeDto);
//				}
//				userTransactionDto.setPointsEarned(userTransactionEntity.getPointsEarned());
//				userTransactionDto.setRemarks(userTransactionEntity.getRemarks());
//
//				userTransactionDto.setTransactionStatus(userTransactionEntity.getTransactionStatus());
//				userTransactionDto.setTransactionDateTime(userTransactionEntity.getTransactionDateTime());
//				userTransactionDto.setUserTransactionId(userTransactionEntity.getUserTransactionId());
//				userTransactionDtos.add(userTransactionDto);
//			}
//		}
//		return userTransactionDtos;
	}
	
	/**
	 * This method is used for redeeming all the reward points
	 * It updates the isRedeemed field of the userTransactionEntity
	 * 
	 * @param userId
	 */
	@Override
	public void redeemAllRewardPoints(Integer userId) {
		
		User user = entityManager.find(User.class, userId);
		List<UserTransactionEntity> transactionEntities = user.getUserTransactionEntities();
		if (transactionEntities != null && !transactionEntities.isEmpty()) {
			for (UserTransactionEntity userTransactionEntity : transactionEntities) {
				if (AmigoWalletConstants.REWARD_POINTS_REDEEMED_NO.equals(userTransactionEntity.getIsRedeemed().toString())){
					userTransactionEntity.setIsRedeemed(AmigoWalletConstants.REWARD_POINTS_REDEEMED_YES.charAt(0));
				}
			}
		}
	}
	
	/**
	 * This method adds the redeemed amount to the wallet
	 * and the userTransaction list is updated
	 * 
	 * @param userId
	 */
	@Override
	public void addRedeemedMoneyToWallet(Integer userId, UserTransactionDto userTransactionDto) {
		
		/*
		 * User entity is fetched using the userId and UserTransaction Entities
		 * for the user entity is fetched and populated to a list
		 */
		User user = entityManager.find(User.class, userId);
		List<UserTransactionEntity> transactionEntities = user.getUserTransactionEntities();
		
		UserTransactionEntity transactionEntity = new UserTransactionEntity();
		transactionEntity.setAmount(userTransactionDto.getAmount());
		transactionEntity.setIsRedeemed(userTransactionDto.getIsRedeemed());
		transactionEntity.setInfo(userTransactionDto.getInfo());
		
		/*
		 * Payment type entity is fetched and set to transaction entity 
		 */
		Query query = entityManager.createQuery(
				"from PaymentTypeEntity where paymentFrom = :paymentFrom and paymentTo = :paymentTo and paymentType = :paymentType");
		query.setParameter("paymentFrom", AmigoWalletConstants.PAYMENT_FROM_WALLET.charAt(0));
		query.setParameter("paymentTo", AmigoWalletConstants.PAYMENT_TO_WALLET.charAt(0));
		query.setParameter("paymentType", AmigoWalletConstants.PAYMENT_TYPE_CREDIT.charAt(0));
		PaymentType paymentType = (PaymentType) query.getSingleResult();
		transactionEntity.setPaymentTypeEntity(paymentType);
		
		transactionEntity.setPointsEarned(userTransactionDto.getPointsEarned());
		transactionEntity.setRemarks(userTransactionDto.getRemarks());
		
		/*
		 * StatusEntity is fetched and set to transaction entity 
		 */
		transactionEntity.setTransactionStatus(TransactionStatusDto.SUCCESS);
		
		/*
		 * The transaction is added to user transactions list
		 */
		transactionEntities.add(transactionEntity);
		user.setUserTransactionEntities(transactionEntities);
	
		/*
		 * The userEntity is saved to the database
		 */
		entityManager.persist(user);
	}

	@Override
	public void updateTransaction(Integer userId,UserTransactionDto userTransactionDto) {
		//Todo update the transction wiht reddem as 'Y'

		RequestEntity requestEntity = RequestEntity
				.post(UriComponentsBuilder.fromUriString(userServiceUrl+"/user/add-transaction/"+userId).build().toUri())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(userTransactionDto);
		ResponseEntity responseEntity = restTemplate.exchange(requestEntity,
				new ParameterizedTypeReference<String>() {});
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new RuntimeException("User Or tranx not found");
		}
	}
}
