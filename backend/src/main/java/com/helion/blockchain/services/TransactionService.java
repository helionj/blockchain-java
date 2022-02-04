package com.helion.blockchain.services;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.helion.blockchain.exceptions.InvalidTransactionException;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.util.Crypto;

@Service
public class TransactionService {

	public boolean validTransaction(Transaction transaction) throws InvalidTransactionException {
		
		Map<String, Double> outputs = transaction.getOutputs();
		
		Double outputTotal = outputs.entrySet().stream().map(t -> t.getValue())
				.reduce(0.0, (val, tot) -> tot+val);
		
		if(!outputTotal.equals(transaction.getInput().getAmount())) {
			System.out.println("Transaction invalid from: "+transaction.getInput().getAddress());
			return false;
		}
		
		try {
			if(!Crypto.verifySignature(outputs.toString(),transaction.getInput().getSiganature(),
					transaction.getInput().getAddress())) {
				System.out.println("Invalid siganture from: "+transaction.getInput().getAddress());
				return false;
			}
		} catch (InvalidKeyException | InvalidKeySpecException | NumberFormatException e) {
			
			throw new InvalidTransactionException("Key invalid");
		}
		return true;
	}
}
