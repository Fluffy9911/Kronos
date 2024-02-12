/**
 * 
 */
package com.kronos.net.data.packet;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.google.gson.Gson;
import com.kronos.io.Config;
import com.kronos.net.data.Packet;

/**
 * 
 */
public class UnsecurePacket extends Packet {
	Config cfg;

	public UnsecurePacket() {
		super();
	}

	public UnsecurePacket(Config cfg) {
		super();
		this.cfg = cfg;
	}

	@Override
	public void dealWithInputData(String dat) {
		Gson g = new Gson();
		System.out.println(dat);
		cfg = g.fromJson(dat, Config.class);
		System.out.println(cfg.toString());
	}

	@Override
	public byte[] send(String algorithm, String input, SecretKey key, IvParameterSpec iv)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		// TODO Auto-generated method stub
		return input.getBytes();
	}

	@Override
	public void receive(String algorithm, byte[] cipherText, SecretKey key, IvParameterSpec iv)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		// TODO Auto-generated method stub
		dealWithInputData(new String(cipherText));
	}

	@Override
	public String getToSend() {

		if (cfg != null) {
			Gson g = new Gson();
			return g.toJson(cfg);

		}
		return "null";
	}

	@Override
	public Object getCurrent() {
		// TODO Auto-generated method stub
		return cfg;
	}

}
