package com.amigowallet.model;

import lombok.Data;

@Data
public class ResponseWrapperDto <T>{

    private String message;
    private T resposne;
}
