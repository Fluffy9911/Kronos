/**
 * 
 */
package com.kronos.net.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.core.util.BufferedStreamReader;

/**
 * 
 */
public class Connection {
	ServerSocket local;
	Socket connection;
	LinkedList<String> databuffer, cbuf;
	InetSocketAddress ss;
	BufferedStreamReader bs, si;
	boolean isServer = true, isConnected = false, isWaiting = true, handshake = false;

	public void setDatabuffer(LinkedList<String> databuffer) {

		this.databuffer = databuffer;
	}

	public void setCbuf(LinkedList<String> cbuf) {

		this.cbuf = cbuf;

	}

	public LinkedList<String> getCbuf() {
		return cbuf;
	}

	public ServerSocket getLocal() {
		return local;
	}

	public Socket getConnection() {
		return connection;
	}

	public void listen() throws IOException {
		bs = new BufferedStreamReader() {

			@Override
			public void onRecieve(String s) {
				databuffer.add(s);
			}

		};
		bs.begin(Executors.newCachedThreadPool(), connection.getInputStream());
	}

	public void listenTerminal() throws IOException {
		si = new BufferedStreamReader() {

			@Override
			public void onRecieve(String s) {
				try {
					listenOnInput(s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		si.begin(Executors.newCachedThreadPool(), System.in);
	}

	public void send(String s) throws IOException {
		PrintWriter out = new PrintWriter(connection.getOutputStream(), false);

		out.println(s);
		System.out.println("Sent: " + s);
		out.flush();
		return;
	}

	public Connection(ServerSocket server) {
		super();
		this.local = server;
		this.isServer = true;

		databuffer = new LinkedList<>();
		cbuf = new LinkedList<>();
	}

	public Connection(InetSocketAddress s) {
		databuffer = new LinkedList<>();
		cbuf = new LinkedList<>();
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
			l.debug("Client: {}", connection.getInetAddress().getHostAddress());
		} catch (IOException e) {
			l.error("IO: an IO error occured. {}", e);

		}
		l.debug("Connected to Client");
	}

	public void tryConnectClient(Logger l) {
		l.debug("Starting Client Connect {}", ss.getAddress().getHostName());
		this.isWaiting = true;
		try {
			this.connection = new Socket();
			this.connection.connect(ss);
			this.isWaiting = false;
			this.isConnected = true;
			l.debug("Connected to server");

		} catch (UnknownHostException e) {
			l.error("UnknownHost: the destination IP could not be resolved. {}", e);
		} catch (IOException e) {
			l.error("IO: an IO error occured. {}", e);

		}
		l.debug("Client connected");

	}

	/**
	 * the buffer behaves like a stack
	 * 
	 * @return
	 */
	public String readNextInBuffer() {
		return databuffer.pop();
	}

	public void listenOnInput(String next) throws IOException {

		if (next.equals("BUF")) {
			for (Iterator iterator = databuffer.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println(string);
			}
			return;
		}
		if (next.equals("END")) {
			this.connection.close();
			return;
		}
		send(next);

	}

	public void connectionError(String s) {
		Kronos.debug.getLogger().debug("A connection error has occured: {}", s);
	}
}
