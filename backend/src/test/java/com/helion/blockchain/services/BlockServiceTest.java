package com.helion.blockchain.services;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.helion.blockchain.models.Block;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.models.TransactionPool;
import com.helion.blockchain.transaction.Wallet;
import com.helion.blockchain.util.Convert;
import com.helion.blockchain.util.Hash;

@SpringBootTest
public class BlockServiceTest {

	private Wallet wallet;
	private Transaction transaction;
	private TransactionPool transactionPool;
	String recipient;
	Double amount;
	Block minedBlock;
	Block genesis;
	Block block;
	
	@Autowired
	private BlockService service;
	
	@Autowired
	private TransactionPoolService transactionPoolService;
	
	@BeforeEach
    void setup() throws Exception{
    	
		wallet = new Wallet();
		recipient = "recipient-pub-key";
		amount = 50.0;
		transaction = new Transaction(wallet,amount, recipient);
		transactionPool = new TransactionPool();
		genesis = new Block(Instant.ofEpochMilli(10000)," ","hash one", transactionPool.getTransactions(), 1, 3);
		transactionPoolService.setTransaction(transaction, transactionPool);
		minedBlock = service.mineBlock(genesis, transactionPool);
    }
	
	@Test
	void minedBlockShouldToHaveCoorectStructure() {
		Assertions.assertNotNull(minedBlock.getTimestamp());
		Assertions.assertNotNull(minedBlock.getLastHash());
		Assertions.assertNotNull(minedBlock.getHash());
		Assertions.assertNotNull(minedBlock.getTransactions());
		Assertions.assertNotNull(minedBlock.getNonce());
		Assertions.assertNotNull(minedBlock.getDifficulty());
	}
	

	@Test
	void minedBlockShouldContainsCorrectValuesOfProperties() {
		Assertions.assertEquals(minedBlock.getTransactions(), transactionPool.getTransactions());
		Assertions.assertEquals(minedBlock.getLastHash(), genesis.getHash());
		
	}
	@Test
	void minedBlockShouldToHaveHashValid() {
		String hash =Hash.sha256(minedBlock.getTimestamp().toString()+minedBlock.getLastHash()+
				minedBlock.getTransactions().toString()+
				String.valueOf(minedBlock.getNonce())+String.valueOf(minedBlock.getDifficulty()));
		
		Assertions.assertEquals(minedBlock.getHash(), hash);
		
	}
	
	@Test
	void minedBlockHashShouldMatchesWithDifficultyCriteria() {
		Assertions.assertEquals(Convert.hexToBin(minedBlock.getHash()).subSequence(0, minedBlock.getDifficulty()), "0".repeat(minedBlock.getDifficulty()));
	}
	
	@Test
	void adjustDifficultyShouldRaisesDifficultyForQuicklyBlock() {
		Long timestamp = genesis.getTimestamp().toEpochMilli()+2900L;
		Assertions.assertEquals(service.adjustDifficulty(genesis, Instant.ofEpochMilli(timestamp)), genesis.getDifficulty()+1);
	}
	
	@Test
	void adjustDifficultyShouldLowersDifficultyForSlowlyBlock() {
		Long timestamp = genesis.getTimestamp().toEpochMilli()+3100;
		Assertions.assertEquals(service.adjustDifficulty(genesis, Instant.ofEpochMilli(timestamp)), genesis.getDifficulty()-1);
	}
	
	
	@Test
	void adjustDifficultyShouldLimitsDifficultyMinToOne() {
		genesis.setDifficulty(-1);
		Assertions.assertEquals(service.adjustDifficulty(genesis,genesis.getTimestamp()), 1);
	}
}
