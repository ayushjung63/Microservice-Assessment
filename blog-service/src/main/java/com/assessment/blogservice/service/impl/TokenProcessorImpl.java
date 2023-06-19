package com.assessment.blogservice.service.impl;

import com.assessment.blogservice.service.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TokenProcessorImpl implements TokenProcessor {
    @Autowired
    private  TokenStore tokenStore;

    @Override
    public List<String> getAuthorities() {
        Map<String, Object> tokenDetail = this.getTokenDetail();
        if (tokenDetail.get("authorities") == null)
            throw new RuntimeException("Permission Denied");
        return (List<String>) tokenDetail.get("authorities");
    }

    private Map<String, Object> getTokenDetail() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            OAuth2AuthenticationDetails auth2AuthenticationDetails =
                    (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            Map<String, Object> details = tokenStore.readAccessToken(auth2AuthenticationDetails.getTokenValue()).getAdditionalInformation();
            return details;
        } else {
            throw new RuntimeException("Something Went Wrong");
        }
    }
}
