package com.amigowallet.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "OFFER")
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CASHBACK_PERCENTAGE")
    private Integer cashBackPercentage;


    @Column(name = "MAX_CASHBACK")
    private Integer maxCashBack;

    @Column(name = "MIN_TRAN_REQ")
    private Integer minTranReq;

    @Column(name = "SERVICE_TYPE_ID")
    private Integer serviceTypeId;

    @Column(name = "STATUS")
    private Boolean isActive;

}
