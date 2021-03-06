package com.selflearning.upscale.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.selflearning.upscale.currencyexchangeservice.Repository.CurrencyExchangeRepo;
import com.selflearning.upscale.currencyexchangeservice.bean.CurrencyExchange;


@RestController
public class ServiceController {
	
	@Value("${myname}")
	String myname;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeRepo repo;
	
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange name(@PathVariable String from, 
								@PathVariable String to) {
		//CurrencyExchange obj =	new CurrencyExchange(1L, "1", "78",new BigDecimal(78));
		
		CurrencyExchange obj = repo.findByFromAndTo(from, to);
		String s = env.getProperty("local.server.port");
		if(obj == null) throw new RuntimeException("OBJ is null. We could not find the give pair.");
		obj.setEnvironment(s);
		
		return obj ;
	}
}
