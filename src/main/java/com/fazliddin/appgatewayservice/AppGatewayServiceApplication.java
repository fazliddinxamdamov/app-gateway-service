package com.fazliddin.appgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppGatewayServiceApplication {

    //todo routelarni qilinishi kerak oxirida.

    public static void main(String[] args) {
        SpringApplication.run(AppGatewayServiceApplication.class, args);
    }

}
