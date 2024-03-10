package com.infy.userservice.controller;


import com.infy.userservice.model.UserDto;
import com.infy.userservice.model.UserTransactionDto;
import com.infy.userservice.service.UserLoginService;
import com.infy.userservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController
{
    @Autowired
    private Environment environment;

    @Autowired
    UserService userService;

    @Autowired
    UserLoginService loginService;

    static Logger logger = LogManager.getLogger(UserLoginController.class.getName());


    //This will be used by Redeem Point service
    @PostMapping("add-transaction/{userId}")
    public ResponseEntity<String> addAndUpdateTransaction(@RequestBody UserTransactionDto userTransactionDto, @PathVariable Integer userId) {
        try {
            userService.addAndUpdateUserTransaction(userId, userTransactionDto);
            return new ResponseEntity<>("Successfully Added the User Transaction", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not able to add the transaction");
        }
    }

    @RequestMapping(value = "userId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
    {
        try {
            UserDto userDto1 = loginService.getUserbyUserId(userId);

            return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);
        }
        catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
        }
    }

    @RequestMapping(value = "email/{email}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
    {
        try {
            UserDto userDto1 = loginService.getUserByEmail(email);

            return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);
        }
        catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
        }
    }

    @PostMapping("add/{userId}")
    public ResponseEntity<String> addTransaction(@RequestBody UserTransactionDto userTransactionDto, @PathVariable Integer userId) {
        try {
            userService.addTransaction(userId, userTransactionDto);
            return new ResponseEntity<>("Successfully Added the User Transaction", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not able to add the transaction");
        }
    }

}

