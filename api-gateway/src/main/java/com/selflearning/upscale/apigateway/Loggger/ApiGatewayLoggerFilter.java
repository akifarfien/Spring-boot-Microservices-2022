package com.selflearning.upscale.apigateway.Loggger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class ApiGatewayLoggerFilter implements GlobalFilter {
	
	private Logger log = LoggerFactory.getLogger(getClass().toString());

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		log.info("Request came on the uri -> {}", exchange.getRequest().getPath());
		
		return chain.filter(exchange);
	}

}
