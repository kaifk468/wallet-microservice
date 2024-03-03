package com.amigowallet.model;

import com.amigowallet.entity.MerchantServiceType;
import lombok.Data;

@Data
public class OfferDto {

    private Integer id;

    private Integer cashBackPercentage;

    private Integer maxCashBack;

    private Integer minTrxReq;

    private Integer serviceTypeId;

    private Boolean isActive;

}
