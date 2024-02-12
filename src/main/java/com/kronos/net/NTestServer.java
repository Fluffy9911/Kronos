/**
 * 
 */
package com.kronos.net;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.kronos.io.Config;
import com.kronos.net.connection.Connector;
import com.kronos.net.data.packet.UnsecurePacket;

/**
 * 
 */
public class NTestServer {
	public static void main(String[] args) {
		Connector cn = new Connector(new InetSocketAddress("192.168.219.9", 255), 255);
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
		Config c = new Config();
		c.appendInt("test_int", 0);
		c.appendLong("time", System.currentTimeMillis());
		cn.c.sendPacket("unsecure", new UnsecurePacket(c));

	}

}
