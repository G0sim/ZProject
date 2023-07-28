package com.example.zproject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotCertifyException extends Exception {

    public NotCertifyException(String message) {
        super(message);
    }
}
