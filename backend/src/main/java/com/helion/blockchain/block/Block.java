package com.helion.blockchain.block;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.helion.blockchain.transaction.Transaction;

public class Block implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private String lastHash;
	private String hash;
	private List<Transaction> transactions;
	private int nonce;
	private int difficulty;
	
	public Block() {}
	
	public Block(Instant timestamp, String lastHash, String hash, List<Transaction> transactions, int nonce,
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
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
	
	
	
	
	
}
