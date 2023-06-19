package com.assessment.gateway.config;

import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;

import java.time.Duration;

/*
* @Configuration annotation is used to define that RoutConfig is Configuration Class
* Configuration Bean are initialised before creation of other beans, so that it can provide dependency required to other beans
* */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                /*
                * redirecting path /login to /oauth/token
                * */
                .route(p->
                        p.path("/login")
                                .filters(f->f.rewritePath("/login","/oauth/token")
                                        .circuitBreaker(config -> config.setFallbackUri("/fallback/auth")))
                                .uri("lb://auth-service") // load balancing
                )
                .route("blog-service",r -> r.path("/blog/**")
                        .filters(f -> f.rewritePath("/blog/(?<remains>.*)", "/${remains}")
                                .circuitBreaker(config -> config.setFallbackUri("/fallback/blog")))
                        .uri("lb://blog-service"))
                .route("auth-service",r -> r.path("/auth/**")
                        .filters(f -> f.rewritePath("/auth/(?<remains>.*)", "/${remains}")
                                .circuitBreaker(config -> config.setFallbackUri("/fallback/auth")))
                        .uri("lb://auth-service"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(60)).build()).build());
    }
}
