package com.helion.blockchain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helion.blockchain.models.Block;
import com.helion.blockchain.models.Transaction;

@Component
public class Blockchain {

	private List<Block> chain = new ArrayList<>();;
	
	
	@Autowired
	int getPropDifficulty;
	
	@Autowired
	int getPropNonce;
	
	public Blockchain() {
		
		
	}
	
	@PostConstruct
	void postContruct() {
		
		Map<String,Transaction> transactions = new HashMap<>();
		Block genesis = new Block(Instant.now()," ","hash one", transactions, getPropNonce, getPropDifficulty);
		chain.add(genesis);
	}



	public List<Block> getChain() {
		return chain;
	}



	@Override
	public String toString() {
		return "Blockchain [chain=" + chain + "]";
	}
	
	
	
}
