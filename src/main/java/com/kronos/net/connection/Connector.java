/**
 * 
 */
package com.kronos.net.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;

/**
 * 
 */
public class Connector {
	InetSocketAddress connect, local;
	Connection c;
	Logger l;

	public Connector(InetSocketAddress connect) {
		super();
		this.connect = connect;
		try {
			local = new InetSocketAddress(InetAddress.getLocalHost(), 0);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.l = Kronos.debug.getLogger();
		try {
			this.c = new Connection(new ServerSocket(), new Socket());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.debug("Connector Created at host: {} Connecting to: {}", local.getHostName(), connect.getHostName());
		l.debug("{} {}", local.getAddress().getHostAddress(), connect.getAddress().getHostAddress());

	}

	public void open(boolean server) {
		if (server) {
			l.debug("Starting Server Connection");
			try {
				this.c.connection = this.c.local.accept();
				l.debug("Connected at: {}", System.currentTimeMillis());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!server) {
			try {
				this.c.local.bind(local);
				l.debug("Bound local address");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
