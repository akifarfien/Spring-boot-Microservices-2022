package com.selflearning.upscale.currencyexchangeservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selflearning.upscale.currencyexchangeservice.bean.CurrencyExchange;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long>{

	//the repo will provide the implementation
	CurrencyExchange findByFromAndTo(String from, String to);
	
	
}
