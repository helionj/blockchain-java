package com.helion.blockchain.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helion.blockchain.exceptions.InvalidTransactionException;
import com.helion.blockchain.models.Block;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.models.TransactionPool;

@Service
public class TransactionPoolService {

	
	@Autowired
	private TransactionService transactionService;


	
	
	public void setTransaction(Transaction transaction, TransactionPool pool) {
		pool.getTransactions().put(transaction.getId(), transaction);
	}

	
	
	public void clear(TransactionPool pool) {
		pool.getTransactions().clear();
	}
	
	
	public Transaction existingTransaction(String address, TransactionPool pool) {
		
	    for(Transaction transaction : pool.getTransactions().values()) {
	    	if(transaction.getOutputs().containsKey(address)) return transaction;	
	    }
		return null;
	}
	
	public Map<String, Transaction> validTransactions(TransactionPool pool) throws InvalidTransactionException {
		
		return pool.getTransactions().entrySet().stream().filter(t -> transactionService.validTransaction(t.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	
	public void clearBlockchainTransactions(List<Block> blocks, TransactionPool pool) {
		
		for(Block block: blocks) {
			 
			Map<String, Transaction> transactions = block.getTransactions();
			for(String t : transactions.keySet()) {
				if(pool.getTransactions().containsKey(t)) {
					pool.getTransactions().remove(t);
				}
			}
		}
	}
	
	
}
