package com.assessment.blogservice.generic;

import com.assessment.blogservice.response.ApiResponse;

/*
* BaseController contains Global Api Response for all Controller
* */
public class BaseController {
    public ApiResponse apiResponse(String message, Object data){
        ApiResponse apiResponse=new ApiResponse(message,data);
        return apiResponse;
    }
}
