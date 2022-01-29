package com.helion.blockchain.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class Crypto {

	private static PublicKey publicKey;
	private static PrivateKey privateKey;
	

	
	public static void init() {
		
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
			ECGenParameterSpec spec = new ECGenParameterSpec("secp256r1");
			keyGen.initialize(spec);
			KeyPair keyPair =  keyGen.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static String sign(String text) {
		
		SecureRandom secureRandom = new SecureRandom();
		Signature signature;
		byte[] digitalSignature = null;
		try {
			signature = Signature.getInstance("SHA256withECDSA");
			signature.initSign(privateKey, secureRandom);
			
			byte[] data = text.getBytes("UTF-8");
			
			signature.update(data);
			digitalSignature = signature.sign();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Convert.bytesToHex(digitalSignature);
		
	}
	
	public static boolean verifySignature(String text, String sign, String publicKey) throws InvalidKeySpecException, InvalidKeyException{
		boolean verified = false;

		try {
			Signature signature = Signature.getInstance("SHA256WithECDSA");
			signature.initVerify(getPublicKey(Convert.hexStringToBytes(publicKey)));
			byte[] data = text.getBytes("UTF-8");
			signature.update(data);
			verified = signature.verify(Convert.hexStringToBytes(sign));
		} catch (NoSuchAlgorithmException |StringIndexOutOfBoundsException
				| UnsupportedEncodingException | SignatureException e) {

			return verified;
		}
		
		return verified;
	}
	
    static PublicKey getPublicKey(byte[] pk) throws NoSuchAlgorithmException, InvalidKeySpecException {
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pk);
        KeyFactory kf = KeyFactory.getInstance("EC");
        PublicKey pub = kf.generatePublic(publicKeySpec);       
        return pub;
    }
    
   
    public static String getPublicKey() {
    	return Convert.bytesToHex(publicKey.getEncoded());
    }
	
    public static String getFormat() {
    	return publicKey.getFormat();
    }
	
	
}
