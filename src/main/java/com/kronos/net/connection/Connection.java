/**
 * 
 */
package com.kronos.net.connection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 */
public class Connection {
	ServerSocket local;
	Socket connection;
	LinkedList<String> databuffer;

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

	public Connection(ServerSocket local, Socket connection) {
		super();
		this.local = local;
		this.connection = connection;
		databuffer = new LinkedList<>();
	}

	public LinkedList<String> getDatabuffer() {
		return databuffer;
	}

}
