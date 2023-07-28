package com.example.zproject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotNullAddPostException extends Exception{

    public NotNullAddPostException (String message){
        super(message);
    }
}
