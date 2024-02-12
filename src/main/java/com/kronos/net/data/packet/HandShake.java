/**
 * 
 */
package com.kronos.net.data.packet;

import javax.crypto.SecretKey;

import com.kronos.io.Config;
import com.kronos.net.connection.Connection;
import com.kronos.net.encryption.EncryptionUtils;

/**
 * 
 */
public class HandShake extends UnsecurePacket {
	Connection c;
	SecretKey key;

	public HandShake(Connection cs) {
		super();
		this.c = cs;
		this.cfg = new Config();
	}

	@Override
	public void initServerSide() {
		try {
			String k = EncryptionUtils.generateAndEncodeSecretKey();
			this.key = EncryptionUtils.decodeSecretKey(k);
			this.cfg.appendString("key", k);
			this.c.setKey(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void recieveClientSide() {
		String key = cfg.readString("key");
		System.out.println(key);
		this.key = EncryptionUtils.decodeSecretKey(key);
		this.c.setKey(this.key);
	}

}
