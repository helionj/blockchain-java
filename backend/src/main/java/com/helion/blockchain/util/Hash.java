package com.helion.blockchain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	
	public static String sha256(String message) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA256");
			md.update(message.getBytes());
			byte[] hashSha256 = md.digest();
			return Convert.bytesToHex(hashSha256);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
