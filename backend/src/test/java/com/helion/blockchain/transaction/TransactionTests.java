package com.helion.blockchain.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.helion.blockchain.models.Transaction;

public class TransactionTests {

	private Wallet wallet;
	private Transaction transaction;
    private Double amount;
    private String recipient;
    
	
	

	@BeforeEach
    void setup() throws Exception{
    	
		wallet = new Wallet();
		recipient = "recipient-pub-key";
		amount = 50.0;
		transaction = new Transaction(wallet,amount, recipient);
    }
	@Test
	void transactionShouldToHaveCorrectStructure() {
		
		Assertions.assertNotNull(transaction.getId());
		Assertions.assertNotNull(transaction.getInput());
		Assertions.assertNotNull(transaction.getOutputs());
	}
	
	@Test
	void transactOutputsShouldToHaveCorrectValues() {
		
		Assertions.assertEquals(transaction.getOutputs().get(wallet.getPublicKey()), wallet.getBalance() - amount);
		Assertions.assertEquals(transaction.getOutputs().get(recipient), amount);
	}
	
	@Test
	void transactionInputShouldToHaveCorrectStructure() {
		Assertions.assertNotNull(transaction.getInput().getAddress());
		Assertions.assertNotNull(transaction.getInput().getAmount());
		Assertions.assertNotNull(transaction.getInput().getTimestamp());
		Assertions.assertNotNull(transaction.getInput().getSiganature());
	}
	
	@Test
	void transactionInputShouldToHaveCorrectValues() {
		Assertions.assertEquals(transaction.getInput().getAddress(), wallet.getPublicKey());
		Assertions.assertEquals(transaction.getInput().getAmount(), wallet.getBalance());
	}
	
	
	
	
	
	
	
	
}
