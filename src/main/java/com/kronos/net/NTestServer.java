/**
 * 
 */
package com.kronos.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;

import com.kronos.io.Config;
import com.kronos.net.connection.Connector;
import com.kronos.net.data.packet.ConfigPacket;

/**
 * 
 */
public class NTestServer {
	public static void main(String[] args) {
		Connector cn = new Connector(new InetSocketAddress("localhost", 255), 255);
		cn.openServer();
		try {
			cn.c.listenTerminal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cn.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cn.c.sendPacket("handshake");
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			Config c = new Config();
			c.appendInt("test_int", r.nextInt(64365436));
			c.appendLong("time", System.currentTimeMillis());
			cn.c.sendPacket("config", new ConfigPacket(c));
		}
	}

}
