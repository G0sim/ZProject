package com.example.zproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexContoller {

    @GetMapping("/")
    public String indexHandle() {
        return "hi!";
    }

}
