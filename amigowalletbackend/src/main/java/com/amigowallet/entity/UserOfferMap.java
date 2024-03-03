package com.amigowallet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USER_OFFER_MAPPING")
@Data
public class UserOfferMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer userId;
    Integer offerId;
}
