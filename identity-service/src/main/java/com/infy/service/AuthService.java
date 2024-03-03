package com.infy.service;

import com.infy.entity.UserCredential;
import com.infy.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);
        return "user added to the system";
    }

    public String generateToken(String username) {
        String email= userCredentialRepository.findByName(username).get().getEmail();
        return jwtService.generateToken(username,email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
