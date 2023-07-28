package com.example.zproject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyCertifyException extends Exception{
    public AlreadyCertifyException (String message){
        super(message);
    }
}
