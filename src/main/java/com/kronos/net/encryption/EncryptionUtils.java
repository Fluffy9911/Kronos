package com.kronos.net.encryption;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {

	public static String generateAndEncodeSecretKey() throws Exception {
		SecretKey secretKey = generateSecretKey();
		return secretKeyToString(secretKey);
	}

	public static SecretKey decodeSecretKey(String encodedKey) {
		byte[] keyBytes = Base64.getDecoder().decode(encodedKey);
		return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
	}

	public static String encryptString(String plaintext, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		if (secretKey == null) {
			throw new NullPointerException("Key is NULL");
		}
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String decryptString(String ciphertext, SecretKey secretKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		if (secretKey == null) {
			throw new NullPointerException("Key is NULL");
		}
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
		return new String(decryptedBytes);
	}

	private static SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256); // You can choose key size based on your security needs
		return keyGenerator.generateKey();
	}

	private static String secretKeyToString(SecretKey secretKey) {
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
}
