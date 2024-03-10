package com.infy.userservice.model;


import lombok.Data;

@Data
public class RequestWrapperDto<T> {

    private T request;

}
