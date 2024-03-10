package com.amigowallet.controller;

import com.amigowallet.model.OfferDto;
import com.amigowallet.model.RequestWrapperDto;
import com.amigowallet.model.ResponseWrapperDto;
import com.amigowallet.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/offer")
@Slf4j
public class OfferController {

    @Autowired
    OfferService offerService;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferDto offerDto){

        try{
            log.info("ADMIN IS TRYING TO CREATE A OFFER");
            offerService.createOffer(offerDto);
            log.info("ADMIN HAS SUCCESFULLY CREATED THE OFFER");
            return new ResponseEntity<OfferDto>(offerDto, HttpStatus.CREATED);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    //Admin can delete the Offer
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseEntity<ResponseWrapperDto> deleteOffer(@RequestBody OfferDto offerDto){
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        try{
            log.info("ADMIN IS TRYING TO DELETE A OFFER");
            offerService.deleteOffer(offerDto);
            log.info("ADMIN HAS SUCCESFULLY DELETED THE OFFER");
            responseWrapperDto.setResposne(offerDto);
            responseWrapperDto.setMessage("Succesfully Deleted the offer");
            return new ResponseEntity<ResponseWrapperDto>(responseWrapperDto,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @RequestMapping(value = "/map",method = RequestMethod.GET)
    public ResponseEntity<ResponseWrapperDto> mapOffer(@RequestParam(value = "offerId", required = true) Integer offerId,
                                                       @RequestParam(value = "userId", required = true) Integer userId){
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        try{
            log.info("ADMIN IS TRYING TO MAP A OFFER");
            offerService.mapOfferToUser(offerId,userId);
            log.info("ADMIN HAS SUCCESFULLY MAPPED THE OFFER");
            responseWrapperDto.setMessage("Succesfully Mapped the offer");
            return new ResponseEntity<ResponseWrapperDto>(responseWrapperDto,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @RequestMapping(value = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<ResponseWrapperDto> getAllOffers(@RequestParam(value = "userId", required = true) Integer userId){
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        try{
            log.info("USER  IS TRYING TO GET ALL HIS OFFER");
            List<OfferDto> offerDtoList=offerService.getAllOffer(userId);
            log.info("USER SUCCESSFULLY GET ALL HIS OFFER");
            responseWrapperDto.setMessage("Succesfully Mapped the offer");
        responseWrapperDto.setResposne(offerDtoList);
            return new ResponseEntity<ResponseWrapperDto>(responseWrapperDto,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
