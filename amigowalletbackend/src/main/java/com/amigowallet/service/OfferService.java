package com.amigowallet.service;

import com.amigowallet.model.OfferDto;
import com.amigowallet.model.UserDto;

import java.util.List;

public interface OfferService {

    public void createOffer(OfferDto offerDto);

    public void deleteOffer(OfferDto offerDto);

    public void mapOfferToUser(Integer offerId, Integer userID);

    public List<OfferDto> getAllOffer(Integer userId);
}
