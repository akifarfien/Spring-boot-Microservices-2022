package com.selflearning.upscale.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	//workin for
	//http://localhost:8765/currency-conversion-Feign/from/USD/to/INR/quantity/10
	//http://localhost:8765/currency-exchange/from/USD/to/INR
	@Bean
	public RouteLocator getwayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes().
				route(p -> p.path("/currency-conversion-Feign/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange-service"))
				
				.build();
		
		
	}

}
