package com.helion.blockchain.util;

public class Convert {

	public static String bytesToHex(byte[] byteArray) {
		StringBuffer hexStringBuffer = new StringBuffer();
	    for (int i = 0; i < byteArray.length; i++) {
	        hexStringBuffer.append(toHex(byteArray[i]));
	    }
	    return hexStringBuffer.toString();
	}
	
	private static String toHex(byte num) {
	    char[] hexDigits = new char[2];
	    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
	    hexDigits[1] = Character.forDigit((num & 0xF), 16);
	    return new String(hexDigits);
	}
	
	
	public static byte[] hexStringToBytes(String hexString) throws StringIndexOutOfBoundsException{
	    byte[] bytes = new byte[hexString.length() / 2];

	    for(int i = 0; i < hexString.length(); i += 2){
	        String sub = hexString.substring(i, i + 2);
	        Integer intVal = Integer.parseInt(sub, 16);
	        bytes[i / 2] = intVal.byteValue();
	        //String hex = "".format("0x%x", bytes[i / 2]);
	    }
	    return bytes;
	}
}
