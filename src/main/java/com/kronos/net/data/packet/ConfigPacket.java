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
public class ConfigPacket extends Packet {
	Config cfg;

	public ConfigPacket() {
		super();
	}

	public ConfigPacket(Config cfg) {
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
		return super.send(algorithm, input, key, iv);
	}

	@Override
	public void receive(String algorithm, byte[] cipherText, SecretKey key, IvParameterSpec iv)
			throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		System.out.println(new String(cipherText));
		super.receive(algorithm, cipherText, key, iv);
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
