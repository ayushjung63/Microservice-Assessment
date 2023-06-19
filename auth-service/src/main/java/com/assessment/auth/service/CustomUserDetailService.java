package com.assessment.auth.service;

import com.assessment.auth.entity.Permission;
import com.assessment.auth.entity.Role;
import com.assessment.auth.entity.User;
import com.assessment.auth.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(
                () -> new RuntimeException("Username or Password is invalid.")
        );

        org.springframework.security.core.userdetails.User user1=   new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                this.getAuthorities(user.getRoles())
        );
        return user1;
    }
    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }

    private List<String> getPermissions(Collection<Role> roles) {
        List<String> permissionList = new ArrayList<>();
        List<Permission> collection = new ArrayList<>();
        for (Role role : roles) {
            permissionList.add(role.getName());
            collection.addAll(role.getPermissionList());
        }
        for (Permission item : collection) {
            permissionList.add(item.getName());
        }
        return permissionList;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> permissionList) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
}
