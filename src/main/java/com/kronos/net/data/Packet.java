/**
 * 
 */
package com.kronos.net.data;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.kronos.net.encryption.EncryptionUtils;

/**
 * 
 */
public abstract class Packet {
	byte[] data;
	long sent, recieved;
	byte pid;
	public static byte MAX_PID = 0;

	public static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecretKeyFactory factory = SecretKeyFactory.getInstance(password);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	public Packet() {
		super();
		sent = -1;
		recieved = -1;
		data = new byte[] {};
		pid = MAX_PID;
		MAX_PID++;

	}

	public byte[] send(String input, SecretKey key) throws Exception {
		this.sent = System.currentTimeMillis();
		return encrypt(input, key);
	}

	/**
	 * handles the inputted data and hands it off to the packet logic to deal with
	 * 
	 * @param algorithm
	 * @param cipherText
	 * @param key
	 * @param iv
	 * @throws Exception
	 */
	public void receive(byte[] input, SecretKey key) throws Exception {
		String dat = decrypt(new String(input), key);
		dealWithInputData(dat);

	}

	public abstract void dealWithInputData(String dat);

	public abstract String getToSend();

	public abstract Object getCurrent();

	@SuppressWarnings("unused")
	private String decrypt(String input, SecretKey key) throws Exception {
		return EncryptionUtils.decryptString(input, key);
	}

	@SuppressWarnings("unused")
	private byte[] encrypt(String input, SecretKey key) throws Exception {

		return EncryptionUtils.encryptString(input, key).getBytes();
	}

	public abstract void initServerSide();

	public abstract void initClientSide();

	public abstract void sentClientSide();

	public abstract void recieveClientSide();

	public abstract void sentServerSide();

	public abstract void recieveServerSide();

}
