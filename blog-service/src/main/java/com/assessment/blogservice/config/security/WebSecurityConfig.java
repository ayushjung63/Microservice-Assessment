//package com.assessment.blogservice.config.security;
//
//import com.assessment.blogservice.config.permission.CustomPermissionEvaluator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(2)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
//    @Bean
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler =
//                new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(permissionEvaluator());
//        return expressionHandler;
//    }
//
//    @Bean
//    public PermissionEvaluator permissionEvaluator() {
//        return new CustomPermissionEvaluator();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .cors().disable()
////                .exceptionHandling()
////                .accessDeniedHandler(accessDeniedHandler)
//        ;
//    }
//}
