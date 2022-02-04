package com.helion.blockchain.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.models.TransactionPool;
import com.helion.blockchain.transaction.Wallet;

@SpringBootTest
public class TransactionPoolServiceTest {

	private Wallet wallet;
	private Transaction transaction;
	private TransactionPool transactionPool;
    private Double amount;
    private String recipient;
    
	@Autowired
	TransactionPoolService service;
	

	@BeforeEach
    void setup() throws Exception{
    	
		wallet = new Wallet();
		recipient = "recipient-pub-key";
		amount = 50.0;
		transaction = new Transaction(wallet,amount, recipient);
		transactionPool = new TransactionPool();
    }
	
	@Test
	void setTransactionShouldAddsTransactionToTransactionPool() {
		
		service.setTransaction(transaction, transactionPool);
		
		Assertions.assertEquals(service.existingTransaction(transaction.getInput().getAddress(), transactionPool), transaction);
	}
	
	@Test
	void validTransactionsShouldReturnAlistTransactionsValid() {
		
		Map<String, Transaction> validTransactions = new HashMap<>();
		
		for(int i =0; i<10; i++) {
			
			transaction = new Transaction(new Wallet(), 50.0, "any-recipient");
			if(i%3 ==0) {
				transaction.getInput().setAmount(9999.0);
			}else if(i%3==1) {
				transaction.getInput().setSiganature("A45878663123");
			}else {
				validTransactions.put(transaction.getId(), transaction);
			}
			
			service.setTransaction(transaction, transactionPool);
		}
		
		Assertions.assertEquals(service.validTransactions(transactionPool), validTransactions);
	}
	
	@Test
	void clearShouldClearsTransactionPool() {
		
		service.setTransaction(transaction, transactionPool);
		service.clear(transactionPool);
		
		Assertions.assertEquals(0, transactionPool.getSize());
	}
}
