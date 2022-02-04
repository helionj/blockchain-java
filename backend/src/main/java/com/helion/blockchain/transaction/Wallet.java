package com.helion.blockchain.transaction;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helion.blockchain.config.AppProperties;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.util.Crypto;

@Component
public class Wallet {
	
	@Autowired
	private AppProperties props;
	
	private String publicKey;
	private Double balance;
    
	
	
	public Wallet() {
		Crypto.init();
		this.publicKey = Crypto.getPublicKey();
		this.balance =500.0;
	}
	
	@PostConstruct
	void posContruct() {
		
		this.balance = props.getPropStartBalance();
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
	public Transaction  createTransaction(String recipient, Double amount) {
		
		return new Transaction(this, amount, recipient);
	}
	
	
	
}
