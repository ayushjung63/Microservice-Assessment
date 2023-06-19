package com.assessment.auth.config;

import com.assessment.auth.entity.Role;
import com.assessment.auth.entity.User;
import com.assessment.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Autowired
    private UserRepo userRepo;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
            User user = userRepo.findByEmail(authentication.getName()).get();
            final Map<String, Object> additionalInfo = new HashMap<String, Object>();
            additionalInfo.put("userId", user.getId());
            additionalInfo.put("email", user.getEmail());
            List<String> roles = new ArrayList<>();
            for (Role roleGroup : user.getRoles()) {
                roles.add(roleGroup.getName());
            }
            additionalInfo.put("roles", roles);
            ((DefaultOAuth2AccessToken) accessToken)
                    .setAdditionalInformation(additionalInfo);
        }
        accessToken = super.enhance(accessToken, authentication);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
        return accessToken;
    }
}
