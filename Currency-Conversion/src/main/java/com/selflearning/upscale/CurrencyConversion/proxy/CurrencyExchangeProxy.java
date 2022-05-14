package com.selflearning.upscale.CurrencyConversion.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.selflearning.upscale.CurrencyConversion.bean.CurrencyConversion;

// to start the load balance just remove url attribute. it will read from eureka server.
//Spring cloud loadbalancer helps feign to load balance and it comes wirh Eureka client.
//@FeignClient(name= "currency-exchange-service", url = "http://localhost:8000")
@FeignClient(name= "currency-exchange-service")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion name(@PathVariable String from, 
								@PathVariable String to);

}
