package com.helion.blockchain.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class Block implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private String lastHash;
	private String hash;
	private Map<String, Transaction> transactions;
	private int nonce;
	private int difficulty;
	
	public Block() {}
	
	public Block(Instant timestamp, String lastHash, String hash, Map<String,Transaction> transactions, int nonce,
			int difficulty) {
		
		this.timestamp = timestamp;
		this.lastHash = lastHash;
		this.hash = hash;
		this.transactions = transactions;
		this.nonce = nonce;
		this.difficulty = difficulty;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getLastHash() {
		return lastHash;
	}

	public void setLastHash(String lastHash) {
		this.lastHash = lastHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Map<String,Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Map<String,Transaction> transactions) {
		this.transactions = transactions;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hash, lastHash);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		return Objects.equals(hash, other.hash) && Objects.equals(lastHash, other.lastHash);
	}
	
	

}
