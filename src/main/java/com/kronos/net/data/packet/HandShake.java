/**
 * 
 */
package com.kronos.net.data.packet;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

import com.kronos.io.Config;
import com.kronos.net.connection.Connection;
import com.kronos.net.data.Packet;

/**
 * 
 */
public class HandShake extends UnsecurePacket {
	Connection c;
	String alg, pass;
	IvParameterSpec spec;

	public HandShake(Connection cs) {
		super();
		this.c = cs;
		pass = rString();
		alg = "AES";
		spec = Packet.generateIv();
	}

	public String rString() {

		int length = 25;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

		return generatedString;
	}

	@Override
	public void dealWithInputData(String dat) {

		super.dealWithInputData(dat);
		alg = cfg.readString("algorithm");
		pass = cfg.readString("password");
		byte[] by = cfg.readString("spec").getBytes();
		spec = new IvParameterSpec(by);

		SecretKey key = new SecretKeySpec(cfg.readString("key").getBytes(), alg);

		c.setKey(key);

		c.setAlgorithm(alg);
		c.setSpec(spec);
		System.out.println("handshake complete!");
	}

	@Override
	public String getToSend() {
		this.cfg = new Config();
		cfg.appendString("algorithm", alg);
		cfg.appendString("password", pass);

		cfg.appendString("spec", new String(spec.getIV()));
		try {
			cfg.appendString("key", new String(getKeyFromPassword(pass, rString()).getEncoded()));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.getToSend();
	}

}