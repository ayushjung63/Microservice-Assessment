package com.assessment.blogservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open-api")
public class OpenController {

    @GetMapping
    public String message(){
        return "I am open API. I do not need Authentication.";
    }
}
