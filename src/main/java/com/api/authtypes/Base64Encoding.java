package com.api.authtypes;

import java.util.Base64;

public class Base64Encoding {

	public static void main(String[] args) {
		
		
		String userNameAndPassword = "mehul:mehul123";
		String base64Encoded = Base64.getEncoder().encodeToString(userNameAndPassword.getBytes());
		System.out.println("Encoded :: "+base64Encoded);
		byte[] decodedBytes = Base64.getDecoder().decode(base64Encoded);
		System.out.println("Decoded :: "+ new String(decodedBytes));

	}

}