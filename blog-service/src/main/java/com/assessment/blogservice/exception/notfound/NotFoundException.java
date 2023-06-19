package com.assessment.blogservice.exception.notfound;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException(String message){
        super(message);
    }
}
