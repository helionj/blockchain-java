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
	
	public static String hexToBin(String sHexString) {
        String sBinString = "";

        for (int i = 0; i < sHexString.length(); i++)
            sBinString += hexCharToBin(sHexString.charAt(i));
        return sBinString;
    }

    public static String hexCharToBin(char c) {
        String sBinStr = "";

        c = Character.toUpperCase(c);

        switch (c) {
        case '0':
            sBinStr = "0000";
            break;
        case '1':
            sBinStr = "0001";
            break;
        case '2':
            sBinStr = "0010";
            break;
        case '3':
            sBinStr = "0011";
            break;
        case '4':
            sBinStr = "0100";
            break;
        case '5':
            sBinStr = "0101";
            break;
        case '6':
            sBinStr = "0110";
            break;
        case '7':
            sBinStr = "0111";
            break;
        case '8':
            sBinStr = "1000";
            break;
        case '9':
            sBinStr = "1001";
            break;
        case 'A':
            sBinStr = "1010";
            break;
        case 'B':
            sBinStr = "1011";
            break;
        case 'C':
            sBinStr = "1100";
            break;
        case 'D':
            sBinStr = "1101";
            break;
        case 'E':
            sBinStr = "1110";
            break;
        case 'F':
            sBinStr = "1111";
            break;
        }
        return sBinStr;
    }
}
