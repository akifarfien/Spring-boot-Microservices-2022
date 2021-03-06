package com.selflearning.upscale.CurrencyConversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.selflearning.upscale.CurrencyConversion.bean.CurrencyConversion;
import com.selflearning.upscale.CurrencyConversion.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@Autowired
	private Environment env;
	
	//REST TEMPLATE
	@GetMapping("/currency-conversion-RestTemplate/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity ) {
		
		HashMap<String, String> map = new HashMap();
		map.put("from", from);
		map.put("to", to);
		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, map);
		CurrencyConversion cc = responseEntity.getBody();
		System.out.println(cc.toString());
		
		return new CurrencyConversion(cc.getId(), cc.getFrom(), cc.getTo(), cc.getConversionMultiple(), quantity ,quantity.multiply(cc.getConversionMultiple()),"");
		
	}
	
	
	
	//OPEN FEIGN
	@GetMapping("/currency-conversion-Feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity ) {
		
		
		CurrencyConversion cc = proxy.name(from, to);
		System.out.println(cc.toString());
		
		return new CurrencyConversion(cc.getId(), cc.getFrom(), cc.getTo(), cc.getConversionMultiple(), quantity ,quantity.multiply(cc.getConversionMultiple()),cc.getEnvironment());
		
	}

}
