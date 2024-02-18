/**
 * 
 */
package com.kronos.net.server;

import java.util.HashMap;

import com.kronos.net.connection.Connector;
import com.kronos.net.data.Packet;

/**
 * 
 */
public abstract class Server {
	HashMap<String, Connector> connections;
	int port;

	public void sendPacket(String client, String id, Packet p) {
		Connector c = connections.get(client);
		if (c.c.isConnected()) {
			c.c.sendPacket(id, p);
		}

	}

	public abstract void recievePacket(String client, String id, Packet sent);

}
