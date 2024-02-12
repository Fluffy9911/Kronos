/**
 * 
 */
package com.kronos.net.data.packet;

import javax.crypto.SecretKey;

import com.kronos.Kronos;
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
			this.cfg = new Config();
			String k = EncryptionUtils.generateAndEncodeSecretKey();
			this.key = EncryptionUtils.decodeSecretKey(k);
			this.cfg.appendString("key", k);
			this.cfg.appendString("version", Kronos.version);
			this.c.setKey(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void recieveClientSide() {
		String key = cfg.readString("key");
		String ver = cfg.readString("version");
		if (!Kronos.version.equals(ver)) {
			plog.warn("Incompatible version on Side: {} Current: {}, Sent: {}", this.c.getSide().toString(),
					Kronos.version, ver);
		}
		this.key = EncryptionUtils.decodeSecretKey(key);
		this.c.setKey(this.key);
		this.plog.debug("Client successfully recieved the servers key");
	}

}
