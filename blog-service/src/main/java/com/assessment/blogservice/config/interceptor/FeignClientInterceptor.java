package com.assessment.blogservice.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/*
* This interceptor intercept each request from blog service to another service and send Bearer token in header.
* */
@Component
class FeignClientInterceptor  implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!= null && authentication.getDetails() instanceof OAuth2AuthenticationDetails){
            OAuth2AuthenticationDetails authenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(
                    AUTHORIZATION_HEADER,
                    String.format("%s %s", TOKEN_TYPE,authenticationDetails.getTokenValue())
            );
        }
    }
}