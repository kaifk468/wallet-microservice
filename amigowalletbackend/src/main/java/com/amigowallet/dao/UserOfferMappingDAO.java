package com.amigowallet.dao;

import com.amigowallet.entity.UserOfferMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOfferMappingDAO extends JpaRepository<UserOfferMap,Integer> {
    List<UserOfferMap> findByUserId(Integer userId);
}
