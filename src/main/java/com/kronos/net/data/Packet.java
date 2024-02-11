/**
 * 
 */
package com.kronos.net.data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 
 */
public abstract class Packet {
	byte[] data;
	long sent, recieved;
	byte pid;
	public static byte MAX_PID = 0;

	public Packet() {
		super();
		sent = -1;
		recieved = -1;
		data = new byte[] {};
		pid = MAX_PID;
		MAX_PID++;

	}

	public byte[] send(String algorithm, String input, SecretKey key, IvParameterSpec iv)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		this.sent = System.currentTimeMillis();
		return encrypt(algorithm, input, key, iv);
	}

	/**
	 * handles the inputted data and hands it off to the packet logic to deal with
	 * 
	 * @param algorithm
	 * @param cipherText
	 * @param key
	 * @param iv
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public void receive(String algorithm, byte[] cipherText, SecretKey key, IvParameterSpec iv)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		String dat = decrypt(algorithm, new String(cipherText), key, iv);
		dealWithInputData(dat);

	}

	public abstract void dealWithInputData(String dat);

	@SuppressWarnings("unused")
	private String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

	@SuppressWarnings("unused")
	private byte[] encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encode(cipherText);
	}
}
