package com.assessment.blogservice.exception.handler;

import com.assessment.blogservice.exception.notfound.NotFoundException;
import com.assessment.blogservice.response.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity handleNotFoundException(NotFoundException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setData(null);
        return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            switch (error.getCode()) {
                case "NotNull":
                    errors.add(error.getField() +" cannot be null");
                    break;
                case "NotBlank":
                    errors.add(error.getField() +" cannot be blank");
                    break;
                default:
                    errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
        }
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ApiResponse apiError = new ApiResponse("Please resolve all errors",errors);
        return handleExceptionInternal(ex, apiError, headers, httpStatus, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleAll(Exception ex){
        if (ex instanceof AccessDeniedHandler){
            ApiResponse apiError=new ApiResponse("Access Denied",null);
            return new ResponseEntity(apiError,HttpStatus.FORBIDDEN);
        }
        ex.printStackTrace();
        ApiResponse apiError=new ApiResponse(ex.getMessage(),null);
        return new ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
