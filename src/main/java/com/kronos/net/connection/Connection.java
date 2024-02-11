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

	public ServerSocket getLocal() {
		return local;
	}

	public Socket getConnection() {
		return connection;
	}

	public void listen() throws IOException {
		Scanner s = new Scanner(connection.getInputStream());
		while (s.hasNext()) {
			databuffer.add(s.next());
		}
		s.close();

	}

	public void send(String s) throws IOException {
		OutputStream os = connection.getOutputStream();
		os.write(s.getBytes());
		os.close();
	}

	public Connection(ServerSocket server) {
		super();
		this.local = server;

		databuffer = new LinkedList<>();
	}

	public Connection(InetSocketAddress s) {
		databuffer = new LinkedList<>();
		ss = s;
	}

	public LinkedList<String> getDatabuffer() {
		return databuffer;
	}

	public void tryConnectServer(Logger l) {
		l.debug("Starting Server Connect");

		try {
			this.connection = local.accept();
			l.debug("Connected! ");
			send("IM Connected!");
		} catch (IOException e) {
			l.debug("Connection failed", e);
		}

	}

	public void tryConnectClient(Logger l) {
		l.debug("Starting Client Connect");

		try {
			this.connection = new Socket(ss.getAddress().getHostAddress(), 0);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
