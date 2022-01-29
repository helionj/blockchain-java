package com.helion.blockchain.util;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CryptoTest {

	private String text = "texto para ser assinado";;
	private String pk;
	private String sign;
   
	@BeforeEach
    void setup() throws Exception{
    	
		Crypto.init();
		pk = Crypto.getPublicKey();
		sign = Crypto.sign(text);
    }
	
	@Test
	void verifySignShouldReturnTrueWhenSignIsValid() throws InvalidKeyException, InvalidKeySpecException {
		
		
		Assertions.assertTrue(Crypto.verifySignature(text, sign, pk));
		
	}
	
	@Test
	void verifySignShouldReturnFalseWhenSignIsInValid() throws InvalidKeyException, InvalidKeySpecException {
		
	
		sign= sign+ "4af45";
		Assertions.assertFalse(Crypto.verifySignature(text, sign, pk));
	}
	
	@Test
	void verifySignShouldReturnFalseWhenPublicKeyIsInValid() throws InvalidKeyException, InvalidKeySpecException {

		pk = pk+"bad";
		Assertions.assertFalse(Crypto.verifySignature(text, sign, pk));
	}

}
