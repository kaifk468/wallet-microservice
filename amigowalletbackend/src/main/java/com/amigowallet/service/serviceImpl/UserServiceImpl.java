package com.amigowallet.service.serviceImpl;

import com.amigowallet.model.UserDto;
import com.amigowallet.model.UserTransactionDto;
import com.amigowallet.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${com.infy.user.service.url}")
    private String userServiceUrl;

    @Override
    @Transactional
    public UserDto getUserByEmailFromUserService(String email) {

        RequestEntity requestEntity = RequestEntity
                .get(UriComponentsBuilder.fromUriString(userServiceUrl+"/user/email/"+email).build().toUri())
                .build();
        ResponseEntity<UserDto> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<UserDto>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }
        else {
            throw new RuntimeException("User Or tranx not found");
        }
    }

    @Override
    @Transactional
    public UserDto getUserByUserIdFromUserService(Integer userId) {
        RequestEntity requestEntity = RequestEntity
                .get(UriComponentsBuilder.fromUriString(userServiceUrl+"/user/userId/"+userId).build().toUri())
                .build();
        ResponseEntity<UserDto> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<UserDto>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }
        else {
            throw new RuntimeException("User Or tranx not found");
        }
    }

    @Override
    public Integer addTransactionToUserService(UserTransactionDto userTransactionDto, Integer userId) {
        RequestEntity requestEntity = RequestEntity
                .post(UriComponentsBuilder.fromUriString(userServiceUrl+"/user/add/"+userId).build().toUri())
                .contentType(MediaType.APPLICATION_JSON)
                .body(userTransactionDto);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<String>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            return 1;
        }
        else {
            throw new RuntimeException("User Or tranx not found");
        }
    }

}
