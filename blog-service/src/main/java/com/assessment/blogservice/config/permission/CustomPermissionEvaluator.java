package com.assessment.blogservice.config.permission;

import com.assessment.blogservice.service.TokenProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CustomPermissionEvaluator  implements PermissionEvaluator {
    @Autowired
    private TokenStore tokenStore;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            /* extract token from Security Context */
            OAuth2AuthenticationDetails auth2AuthenticationDetails =
                    (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            Map<String, Object> details = tokenStore.readAccessToken(auth2AuthenticationDetails.getTokenValue()).getAdditionalInformation();

            //if authorities is null in token, access denied
            if (details.get("authorities") == null) return false;

            List<String> permissionList = (List<String>) details.get("authorities");
            String permissionName = String.valueOf(permission);
            return permissionList.contains(permissionName); //returns true if permissionList contain mentioned permission
        } else {
            throw new RuntimeException("Something Went Wrong");
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
