/**
 * 
 */
package com.kronos.net.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;

/**
 * 
 */
public class Connector {
	InetSocketAddress connect;
	Connection c;
	Logger l;
	int po;

	public Connector(InetSocketAddress connect, int p) {
		super();
		this.connect = connect;
		po = p;
		this.l = Kronos.debug.getLogger();
	}

	public void openServer() {
		try {
			this.c = new Connection(new ServerSocket(po));
			l.debug("Server Opened");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.c.tryConnectServer(l);
	}

	public void openClient() {
		this.c = new Connection(connect);

		this.c.tryConnectClient(l);
	}

	public void send(String d) {
		try {
			this.c.send(d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
