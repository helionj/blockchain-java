package com.helion.blockchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helion.blockchain.Blockchain;
import com.helion.blockchain.dto.TransactionDTO;
import com.helion.blockchain.models.Block;
import com.helion.blockchain.models.Transaction;
import com.helion.blockchain.transaction.Wallet;

@RestController()
@RequestMapping(value="/api")
public class BlockchainController {

	@Autowired
	private Blockchain blockchain;
	
	@Autowired
	private Wallet wallet;
	
	@GetMapping("/blocks")
	ResponseEntity<List<Block>> getBlocks(){
		return ResponseEntity.ok(blockchain.getChain());
	}
	
	@PostMapping("/transact")
	ResponseEntity<Transaction>insert(@RequestBody TransactionDTO dto){
		
		Transaction transaction = wallet.createTransaction(dto.getRecipient(), dto.getAmount());
		return ResponseEntity.ok(transaction);
		
	}
	
}
