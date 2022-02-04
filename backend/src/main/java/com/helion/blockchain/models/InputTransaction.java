package com.helion.blockchain.models;

import java.time.Instant;

public class InputTransaction {

	private Instant timestamp;
	private String address;
	private Double amount;
	private String siganature;
	
	
	public InputTransaction() {}

    
	public InputTransaction(Instant timestamp, String address, Double amount, String siganature) {
		
		this.timestamp = timestamp;
		this.address = address;
		this.amount = amount;
		this.siganature = siganature;
	}


	public Instant getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getSiganature() {
		return siganature;
	}


	public void setSiganature(String siganature) {
		this.siganature = siganature;
	}
	
	
}
