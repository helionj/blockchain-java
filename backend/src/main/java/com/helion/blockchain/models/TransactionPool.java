package com.helion.blockchain.models;

import java.util.HashMap;
import java.util.Map;

public class TransactionPool {
	
	
	private Map<String, Transaction> transactions = new HashMap<>();
	
	
	public TransactionPool() {}
	
	public Map<String, Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Map<String, Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public int getSize() {
		return transactions.size();
	}
}
