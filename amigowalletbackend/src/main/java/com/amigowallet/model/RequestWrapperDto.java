package com.amigowallet.model;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class RequestWrapperDto<T> {

    private T request;

}
