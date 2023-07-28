package com.example.zproject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NonExistentPostException extends Exception {
    public NonExistentPostException(String message) {
        super(message);
    }

}
