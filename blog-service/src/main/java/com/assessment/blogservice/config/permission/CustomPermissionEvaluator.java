package com.assessment.blogservice.config.permission;

import com.assessment.blogservice.service.TokenProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.TokenService;

import java.io.Serializable;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomPermissionEvaluator  implements PermissionEvaluator {
    @Autowired
    private TokenProcessor tokenProcessor;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        List<String> authorities = tokenProcessor.getAuthorities();
        for (String perm : authorities){
            //As Authority Are Assigned as ControllerName_PermissionName so
            Boolean hasAccess = authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals(perm));
            if(hasAccess) return true;
            //Else Loop Will be continued
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
