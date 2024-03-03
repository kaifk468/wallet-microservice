package com.edubank.model;


import lombok.Data;

@Data
public class RequestWrapper<T> {

    private T request;
}