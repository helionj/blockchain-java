package com.helion.blockchain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.helion.blockchain.exceptions.InvalidTransactionException;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.transaction.Wallet;

@SpringBootTest
public class TransactionServiceTest {

	private Wallet wallet;
	private Transaction transaction;
    private Double amount;
    private String recipient;
    
	@Autowired
	private TransactionService service;

	@BeforeEach
    void setup() throws Exception{
    	
		wallet = new Wallet();
		recipient = "recipient-pub-key";
		amount = 50.0;
		transaction = new Transaction(wallet,amount, recipient);
    }
	
	@Test
	void validTransactionShouldReturnTrueWhenTransactionIsValid() throws Exception{
		
		Assertions.assertTrue(service.validTransaction(transaction));
	}
	
	@Test
	void validTransactionShouldReturnFalseWhenTotalAmountOutputsNotEqualInputAmount() throws Exception{
		
		transaction.getInput().setAmount(100.0);
		
		Assertions.assertFalse(service.validTransaction(transaction));
	}
	
	@Test
	void validTransactionShouldReturnFalseWhenSignatureIsInvalid() throws Exception{
		
		transaction.getOutputs().replace(recipient, 1000.0);
		Assertions.assertFalse(service.validTransaction(transaction));
	}
	
	@Test
	void validTransactionShouldThrowsInvalidKeyExceptionWhenPublickKeyIsInvalid() throws Exception{
		transaction.getInput().setAddress("3059acef");
		
		 Exception exception = Assertions.assertThrows(InvalidTransactionException.class, () -> {
		        service.validTransaction(transaction);
		    });

		    String expectedMessage = "Key invalid";
		    String actualMessage = exception.getMessage();

		    Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void validTransactionShouldThrowsInvalidKeyExceptionWhenPublickKeyIsInvalidText() throws Exception{
		transaction.getInput().setAddress("bad-key");
		
		 Exception exception = Assertions.assertThrows(InvalidTransactionException.class, () -> {
		        service.validTransaction(transaction);
		    });

		    String expectedMessage = "Key invalid";
		    String actualMessage = exception.getMessage();

		    Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}
}
