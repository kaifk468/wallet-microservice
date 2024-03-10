package com.infy.userservice.service.serviceImpl;

import com.infy.userservice.entity.PaymentType;
import com.infy.userservice.entity.User;
import com.infy.userservice.entity.UserTransactionEntity;
import com.infy.userservice.model.UserTransactionDto;
import com.infy.userservice.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void addAndUpdateUserTransaction(Integer userId,UserTransactionDto userTransactionDto) {
        User user =entityManager.find(User.class,userId);
        List<UserTransactionEntity> userTransactionEntityList=user.getUserTransactionEntities();
        //Updating user Transaction
        for(UserTransactionEntity userTransactionEntity:userTransactionEntityList){
            userTransactionEntity.setIsRedeemed('Y');
        }

        //Adding new Transaction
        UserTransactionEntity userTransactionEntity=new UserTransactionEntity();

        userTransactionEntity.setUserTransactionId(userTransactionDto.getUserTransactionId());
        userTransactionEntity.setTransactionStatusDto(userTransactionDto.getTransactionStatus());
        userTransactionEntity.setInfo(userTransactionDto.getInfo());
        userTransactionEntity.setAmount(userTransactionDto.getAmount());
        userTransactionEntity.setTransactionDateTime(userTransactionDto.getTransactionDateTime());
        userTransactionEntity.setPointsEarned(userTransactionDto.getPointsEarned());
        userTransactionEntity.setRemarks(userTransactionDto.getRemarks());
        userTransactionEntity.setIsRedeemed(userTransactionDto.getIsRedeemed());

        PaymentType paymentType=new PaymentType();
        paymentType.setPaymentTypeId(userTransactionDto.getPaymentType().getPaymentTypeId());
        userTransactionEntity.setPaymentType(paymentType);

        userTransactionEntityList.add(userTransactionEntity);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void addTransaction(Integer userId, UserTransactionDto userTransactionDto) {
        User user =entityManager.find(User.class,userId);
        List<UserTransactionEntity> userTransactionEntityList=user.getUserTransactionEntities();

        //Adding new Transaction
        UserTransactionEntity userTransactionEntity=new UserTransactionEntity();

        userTransactionEntity.setUserTransactionId(userTransactionDto.getUserTransactionId());
        userTransactionEntity.setTransactionStatusDto(userTransactionDto.getTransactionStatus());
        userTransactionEntity.setInfo(userTransactionDto.getInfo());
        userTransactionEntity.setAmount(userTransactionDto.getAmount());
        userTransactionEntity.setTransactionDateTime(userTransactionDto.getTransactionDateTime());
        userTransactionEntity.setPointsEarned(userTransactionDto.getPointsEarned());
        userTransactionEntity.setRemarks(userTransactionDto.getRemarks());
        userTransactionEntity.setIsRedeemed(userTransactionDto.getIsRedeemed());

        PaymentType paymentType=new PaymentType();
        paymentType.setPaymentTypeId(userTransactionDto.getPaymentType().getPaymentTypeId());
        userTransactionEntity.setPaymentType(paymentType);

        userTransactionEntityList.add(userTransactionEntity);
        entityManager.persist(user);
    }
}
