package com.helion.blockchain.services;

import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helion.blockchain.models.Block;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.models.TransactionPool;
import com.helion.blockchain.util.Convert;
import com.helion.blockchain.util.Hash;

@Service
public class BlockService {

	@Autowired
	int getPropMineRate;

	public Block mineBlock(Block lastBlock, TransactionPool pool) {

		int nonce = 0;
		int difficulty = 0;
		String hash;
		Instant timestamp;
		String lastHash = lastBlock.getHash();
		Map<String, Transaction> transactions = pool.getTransactions();

		do {

			timestamp = Instant.now();
			difficulty = adjustDifficulty(lastBlock, timestamp);
			nonce++;
			hash = Hash.sha256(timestamp.toString() + lastHash + transactions.toString() + String.valueOf(nonce)
					+ String.valueOf(difficulty));

		} while (!Convert.hexToBin(hash).substring(0, difficulty).equals("0".repeat(difficulty)));

		return new Block(timestamp, lastHash, hash, transactions, nonce, difficulty);
	}

	public int adjustDifficulty(Block lastBlock, Instant timestamp) {

		int difficulty = lastBlock.getDifficulty();
		if (difficulty < 1)
			return 1;
		long difTime = timestamp.toEpochMilli() - lastBlock.getTimestamp().toEpochMilli();
		if (difTime > 3000)
			return difficulty - 1;
		return difficulty + 1;
	}
}
