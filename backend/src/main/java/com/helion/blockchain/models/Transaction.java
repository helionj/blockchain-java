package com.helion.blockchain.models;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.helion.blockchain.transaction.Wallet;
import com.helion.blockchain.util.Crypto;


public class Transaction {
	
	private String id;
	private InputTransaction input;
	private Map<String, Double> outputs = new HashMap<>();
	
	public Transaction(Wallet senderWallet, Double amount, String recipient ) {
		this.id = UUID.randomUUID().toString();
		this.createOutput(senderWallet, recipient, amount);
		this.createInput(senderWallet, amount, recipient );
	
	}
	
	private void createInput(Wallet senderWallet, Double amount, String recipient) {
		String signature = Crypto.sign(outputs.toString());
		
		this.input = new InputTransaction( Instant.now(), senderWallet.getPublicKey(), 
				senderWallet.getBalance(), signature );
	}
	
	private void createOutput( Wallet senderWallet, String recipient,Double amount){
	        
		Double balance = senderWallet.getBalance() - amount;
		outputs.put(recipient, amount);
		outputs.put(senderWallet.getPublicKey(), balance);
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputTransaction getInput() {
		return input;
	}

	public void setInput(InputTransaction input) {
		this.input = input;
	}

	public Map<String, Double> getOutputs() {
		return outputs;
	}

	public void setOutputs(Map<String, Double> outputs) {
		this.outputs = outputs;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	

}
