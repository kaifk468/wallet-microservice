package com.amigowallet.service.serviceImpl;

import com.amigowallet.dao.OfferDAO;
import com.amigowallet.dao.UserOfferMappingDAO;
import com.amigowallet.entity.Offer;
import com.amigowallet.entity.UserOfferMap;
import com.amigowallet.model.OfferDto;
import com.amigowallet.model.UserDto;
import com.amigowallet.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    OfferDAO offerDAO;

    @Autowired
    UserOfferMappingDAO userOfferMappingDAO;

    @Override
    public void createOffer(OfferDto offerDto) {
        Offer offer =new Offer();
        offer.setIsActive(true);
        offer.setServiceTypeId(offerDto.getServiceTypeId());
        offer.setCashBackPercentage(offerDto.getCashBackPercentage());
        offer.setMinTranReq(offerDto.getMinTrxReq());
        offer.setMaxCashBack(offerDto.getMaxCashBack());
        offerDAO.save(offer);
    }

    @Override
    public void deleteOffer(OfferDto offerDto) {

        Offer offer=new Offer();
        offer.setId(offerDto.getId());
        offerDAO.delete(offer);
    }

    @Override
    public void mapOfferToUser(Integer offerId, Integer userId) {
        UserOfferMap userOfferMap=new UserOfferMap();
        userOfferMap.setUserId(userId);
        userOfferMap.setOfferId(offerId);
        userOfferMappingDAO.save(userOfferMap);
    }

    @Override
    public List<OfferDto> getAllOffer(Integer userId) {

        List<UserOfferMap> userOfferMapList=userOfferMappingDAO.findByUserId(userId);
        List<OfferDto> offerDtoList=new ArrayList<>();
        for(UserOfferMap userOfferMap: userOfferMapList){

            Offer offer=offerDAO.findById(userOfferMap.getOfferId()).get();
            OfferDto offerDto=new OfferDto();
            offerDto.setId(offer.getId());
            offerDto.setServiceTypeId(offer.getServiceTypeId());
            offerDto.setIsActive(offer.getIsActive());
            offerDto.setMaxCashBack(offer.getMaxCashBack());
            offerDto.setCashBackPercentage(offer.getCashBackPercentage());
            offerDto.setMinTrxReq(offer.getMinTranReq());

            offerDtoList.add(offerDto);
        }
        return offerDtoList;
    }
}
