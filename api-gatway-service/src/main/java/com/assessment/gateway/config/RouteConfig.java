package com.assessment.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* @Configuration annotation is used to define that RoutConfig is Configuration Class
* Configuration Bean are initialised before creation of other beans, so that it can provide dependency required to other beans
* */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->
                        p.path("/login")
                                .filters(f->f.rewritePath("/login","/oauth/token"))
                                .uri("lb://auth-service")
                )
                .build();
    }
}
