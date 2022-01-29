package com.helion.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.helion.blockchain.util.Convert;
import com.helion.blockchain.util.Crypto;


@SpringBootApplication
public class BlockchainApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, SignatureException, NoSuchProviderException {
		
		
		String text = "Texto para ser assinado";
		
		Crypto.init();
		
		String pubKey = Crypto.getPublicKey();
		
		
		String sign = Crypto.sign(text);
		
		//System.out.println(crypto.getFormat());
		sign="4f5678";
		
		try {
			if(Crypto.verifySignature(text, sign, pubKey)) System.out.println("Verificado");
		} catch (InvalidKeyException | InvalidKeySpecException e) {
			System.out.println("Chave inválida");
		}
		
		SpringApplication.run(BlockchainApplication.class, args);
	}

}
