package com.fazliddin.appgatewayservice.contrller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  10:01
 * @project app-fast-food
 */

@Configuration
public class RouteConfig {
    private final String gatewayServiceUsername = "gatewayServiceUsername";
    private final String gatewayServicePassword = "gatewayServicePassword";

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/auth/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config.setName("fastFoodCallback").setFallbackUri("/fallback"))
                                .addRequestHeader("serviceUsername", gatewayServiceUsername)
                                .addRequestHeader("servicePassword", gatewayServicePassword))
                        .uri("lb://AUTH-SERVICE"))
                .route(r -> r
                        .path("/api/branch/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config.setName("fastFoodCallback").setFallbackUri("/fallback"))
                                .addRequestHeader("serviceUsername", gatewayServiceUsername)
                                .addRequestHeader("servicePassword", gatewayServicePassword))
                        .uri("lb://BRANCH-SERVICE"))
                .route(r -> r
                        .path("/api/order/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config.setName("fastFoodCallback").setFallbackUri("/fallback"))
                                .addRequestHeader("serviceUsername", gatewayServiceUsername)
                                .addRequestHeader("servicePassword", gatewayServicePassword))
                        .uri("lb://ORDER-SERVICE"))
                .route(r -> r
                        .path("/api/product/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config.setName("fastFoodCallback").setFallbackUri("/fallback"))
                                .addRequestHeader("serviceUsername", gatewayServiceUsername)
                                .addRequestHeader("servicePassword", gatewayServicePassword)
                        )
                        .uri("lb://PRODUCT-SERVICE"))
                .build();

    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(60))
                        .build())
                .build());
    }
}
