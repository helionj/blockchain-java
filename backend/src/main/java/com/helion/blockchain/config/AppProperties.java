package com.helion.blockchain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	
	@Value("${block.mine-rate:3000}")
	private int propMineRate;
	
	@Value("${block.difficulty:1}")
	private int propDifficulty;
	
	@Value("${block.nonce:0}")
	private int propNonce;
	
	@Value("${wallet.start-balance:500.0}")
	private Double propStartBalance;
	


	@Bean
	public int getPropDifficulty() {
		return propDifficulty;
	}

	
	@Bean
	public int getPropNonce() {
		return propNonce;
	}

    @Bean
	public Double getPropStartBalance() {
		return propStartBalance;
	}

    @Bean
	public int getPropMineRate() {
		return propMineRate;
	}

	
	
	
}
