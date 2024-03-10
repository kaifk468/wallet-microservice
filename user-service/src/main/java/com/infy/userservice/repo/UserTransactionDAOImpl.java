package com.infy.userservice.repo;


import com.infy.userservice.entity.PaymentType;
import com.infy.userservice.entity.User;
import com.infy.userservice.entity.UserTransactionEntity;
import com.infy.userservice.model.*;
import com.infy.userservice.util.AmigoWalletConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * This is a DAO class having method to perform CRUD operations on user and transactions
 * on performing different transactions like loadMoney, debitMoney etc.
 * 
 * @author ETA_JAVA
 * 
 */
@Repository("userTransactionDAO")
public class UserTransactionDAOImpl implements UserTransactionDAO {
	
	/** This is a spring auto-wired attribute used to create data base sessions */
	@Autowired
	EntityManager entityManager;

	@Autowired
	RestTemplate restTemplate;

	@Value("${com.infy.bank.money.debit.url}")
	String loadMoneyUrl;

	@Value("${com.infy.bank.money.credit.url}")
	String sentMoneyUrl;

    @Override
	public UserTransactionDto loadMoneyToWallet(UserTransactionDto userTransactionDto, MoneyRequestDto moneyRequestDto) {

		User user = entityManager.find(User.class, Integer.parseInt(moneyRequestDto.getUserId()));

		RequestWrapperDto requestWrapperDto = new RequestWrapperDto();
		requestWrapperDto.setRequest(moneyRequestDto);
		RequestEntity requestEntity = RequestEntity
				.post(UriComponentsBuilder.fromUriString(loadMoneyUrl).build().toUri())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(requestWrapperDto);
		ResponseEntity<Map<String,Object>> responseEntity = restTemplate.exchange(requestEntity,
				new ParameterizedTypeReference<Map<String,Object>>() {});

		if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
			userTransactionDto.setInfo("Something went wrong");
			userTransactionDto.setRemarks("Transaction Failed");
			return userTransactionDto;
		}
		List<UserTransactionEntity> transactionEntities = user.getUserTransactionEntities();

		UserTransactionEntity transactionEntity = new UserTransactionEntity();
		transactionEntity.setAmount(userTransactionDto.getAmount());
		transactionEntity.setIsRedeemed(userTransactionDto.getIsRedeemed());
		transactionEntity.setInfo(userTransactionDto.getInfo());

		/*
		 * The following code is to set the paymentTypeEntity to transactionEntity
		 */

		Query query = entityManager.createQuery(
				"from PaymentType where paymentFrom = :paymentFrom and paymentTo = :paymentTo and paymentType = :paymentType");
		query.setParameter("paymentFrom", AmigoWalletConstants.PAYMENT_FROM_BANK.charAt(0));
		query.setParameter("paymentTo", AmigoWalletConstants.PAYMENT_TO_WALLET.charAt(0));
		query.setParameter("paymentType", AmigoWalletConstants.PAYMENT_TYPE_CREDIT.charAt(0));
		PaymentType paymentTypeEntity = (PaymentType) query.getSingleResult();

		transactionEntity.setPaymentType(paymentTypeEntity);
		transactionEntity.setPointsEarned(userTransactionDto.getPointsEarned());
		transactionEntity.setRemarks(userTransactionDto.getRemarks());

		/*
		 * The following code is to set the StatusEntity to transactionEntity
		 */

		transactionEntity.setTransactionStatusDto(TransactionStatusDto.SUCCESS);

		transactionEntities.add(transactionEntity);
		entityManager.persist(transactionEntity);
		user.setUserTransactionEntities(transactionEntities);

		/*
		 * The userEntity is saved to the database
		 */
		entityManager.persist(user);
		userTransactionDto.setUserTransactionId(transactionEntity.getUserTransactionId());

		/*
		 * The following code is to set the paymenType model object to transaction model object
		 */
		if (paymentTypeEntity != null) {

			PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
			paymentTypeDto.setPaymentFrom(paymentTypeEntity.getPaymentFrom());
			paymentTypeDto.setPaymentTo(paymentTypeEntity.getPaymentTo());
			paymentTypeDto.setPaymentType(paymentTypeEntity.getPaymentType());
			paymentTypeDto.setPaymentTypeId(paymentTypeEntity.getPaymentTypeId());

			userTransactionDto.setPaymentType(paymentTypeDto);
		}

		userTransactionDto.setTransactionStatus(transactionEntity.getTransactionStatusDto());
		return userTransactionDto;
	}

	@Override
	public UserTransactionDto sendMoneyToBank(
			UserTransactionDto userTransactionDto, MoneyRequestDto moneyRequestDto) {

		User user = entityManager.find(User.class, Integer.parseInt(moneyRequestDto.getUserId()));


		RequestWrapperDto requestWrapperDto = new RequestWrapperDto();
		requestWrapperDto.setRequest(moneyRequestDto);
		RequestEntity requestEntity = RequestEntity
				.post(UriComponentsBuilder.fromUriString(sentMoneyUrl).build().toUri())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(requestWrapperDto);
		ResponseEntity<Map<String,Object>> responseEntity = restTemplate.exchange(requestEntity,
				new ParameterizedTypeReference<Map<String,Object>>() {});

		if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
			userTransactionDto.setInfo("Something went wrong");
			userTransactionDto.setRemarks("Transaction Failed");
			return userTransactionDto;
		}

		List<UserTransactionEntity> transactionEntities = user.getUserTransactionEntities();

		UserTransactionEntity transactionEntity = new UserTransactionEntity();
		transactionEntity.setAmount(userTransactionDto.getAmount());
		transactionEntity.setIsRedeemed(userTransactionDto.getIsRedeemed());
		transactionEntity.setInfo(userTransactionDto.getInfo());

		/*
		 * The following code is to set the paymentTypeEntity to transactionEntity
		 */

		Query query = entityManager.createQuery(
				"from PaymentType where paymentFrom = :paymentFrom and paymentTo = :paymentTo and paymentType = :paymentType");

		query.setParameter("paymentFrom", AmigoWalletConstants.PAYMENT_FROM_WALLET.charAt(0));
		query.setParameter("paymentTo", AmigoWalletConstants.PAYMENT_TO_BANK.charAt(0));
		query.setParameter("paymentType", AmigoWalletConstants.PAYMENT_TYPE_DEBIT.charAt(0));

		PaymentType paymentTypeEntity = (PaymentType) query.getSingleResult();

		transactionEntity.setPaymentType(paymentTypeEntity);
		transactionEntity.setPointsEarned(userTransactionDto.getPointsEarned());
		transactionEntity.setRemarks(userTransactionDto.getRemarks());

		/*
		 * The following code is to set the StatusEntity to transactionEntity
		 */

		transactionEntity.setTransactionStatusDto(TransactionStatusDto.SUCCESS);

		transactionEntities.add(transactionEntity);
		entityManager.persist(transactionEntity);
		user.setUserTransactionEntities(transactionEntities);

		/*
		 * The userEntity is saved to the database
		 */
		entityManager.persist(user);
		userTransactionDto.setUserTransactionId(transactionEntity.getUserTransactionId());

		/*
		 * The following code is to set the paymenType model object to transaction model object
		 */
		if (paymentTypeEntity != null) {

			PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
			paymentTypeDto.setPaymentFrom(paymentTypeEntity.getPaymentFrom());
			paymentTypeDto.setPaymentTo(paymentTypeEntity.getPaymentTo());
			paymentTypeDto.setPaymentType(paymentTypeEntity.getPaymentType());
			paymentTypeDto.setPaymentTypeId(paymentTypeEntity.getPaymentTypeId());

			userTransactionDto.setPaymentType(paymentTypeDto);
		}

		userTransactionDto.setTransactionStatus(transactionEntity.getTransactionStatusDto());
		return userTransactionDto;
	}
}
