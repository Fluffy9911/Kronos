/**
 * 
 */
package com.kronos.net.connection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;

/**
 * 
 */
public class Connection {
	ServerSocket local;
	Socket connection;
	LinkedList<String> databuffer;
	InetSocketAddress ss;

	boolean isServer = true, isConnected = false, isWaiting = true, handshake = false;

	public ServerSocket getLocal() {
		return local;
	}

	public Socket getConnection() {
		return connection;
	}

	@SuppressWarnings("resource")
	public void listen() throws IOException {

		Scanner s = new Scanner(connection.getInputStream());
		while (s.hasNext()) {
			databuffer.add(s.next());
		}

		for (Iterator iterator = databuffer.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

	public void send(String s) throws IOException {
		OutputStream os = connection.getOutputStream();
		os.write(s.getBytes());

	}

	public Connection(ServerSocket server) {
		super();
		this.local = server;
		this.isServer = true;

		databuffer = new LinkedList<>();
	}

	public Connection(InetSocketAddress s) {
		databuffer = new LinkedList<>();
		ss = s;
		this.isServer = false;
	}

	public LinkedList<String> getDatabuffer() {
		return databuffer;
	}

	public void tryConnectServer(Logger l) {
		l.debug("Starting Server Connect");
		this.isWaiting = true;
		try {
			this.connection = local.accept();
			l.debug("Connected! ");
			this.isWaiting = false;
			this.isConnected = true;
		} catch (IOException e) {
			l.error("IO: an IO error occured. {}", e);

		}

	}

	public void tryConnectClient(Logger l) {
		l.debug("Starting Client Connect {}", ss.getAddress().getHostName());
		this.isWaiting = true;
		try {
			this.connection = new Socket();
			this.connection.connect(ss);
			this.isWaiting = false;
			this.isConnected = true;
		} catch (UnknownHostException e) {
			l.error("UnknownHost: the destination IP could not be resolved. {}", e);
		} catch (IOException e) {
			l.error("IO: an IO error occured. {}", e);

		}

	}

	/**
	 * the buffer behaves like a stack
	 * 
	 * @return
	 */
	public String readNextInBuffer() {
		return databuffer.pop();
	}

}
