package com.helion.blockchain.dto;

public class TransactionDTO {

	private String recipient;
	private Double amount;
	
	public TransactionDTO() {}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
