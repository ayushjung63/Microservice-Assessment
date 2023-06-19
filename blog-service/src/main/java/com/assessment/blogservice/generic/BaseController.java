package com.assessment.blogservice.generic;

import com.assessment.blogservice.response.ApiResponse;

public class BaseController {
    public ApiResponse apiResponse(String message, Object data){
        ApiResponse apiResponse=new ApiResponse(message,data);
        return apiResponse;
    }
}
