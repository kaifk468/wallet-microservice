package com.amigowallet.dao;

import com.amigowallet.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferDAO extends JpaRepository<Offer,Integer> {
}
