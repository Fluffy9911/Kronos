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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Executors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.core.util.BufferedStreamReader;
import com.kronos.net.data.Packet;
import com.kronos.net.data.packet.UnsecurePacket;

/**
 * 
 */
public class Connection {
	ServerSocket local;
	Socket connection;
	LinkedList<String> databuffer, cbuf;
	InetSocketAddress ss;
	BufferedStreamReader bs, si;

	HashMap<String, Packet> registered;

	boolean isServer = true, isConnected = false, isWaiting = true, handshake = false;
	private ArrayList<String> datalog;

	{

		datalog = new ArrayList<>();
		registered = new HashMap<>();
		registered.put("unsecure", new UnsecurePacket());
	}
	String cp;

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
				if (registered.containsKey(s)) {
					cp = s;

				} else {
					cp = "null";
				}
				if (!cp.equals("null") && cp != s) {
					Packet p = registered.get(cp);
					try {
						p.receive(s, s.getBytes(), null, null);
						System.out.println("packet recieved");
					} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
							| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				appendData("input msg", s, "CONNECTION RECIEVED DATA");
				databuffer.add(s);
			}

		};
		bs.begin(Executors.newCachedThreadPool(), connection.getInputStream());
	}

	public void listenTerminal() throws IOException {
		si = new BufferedStreamReader() {

			@Override
			public void onRecieve(String s) {
				appendData("input terminal", s, "TERMINAL RECIEVED DATA");
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

	public void sendPacket(String p) {

		try {
			send(p);
			Packet pa = registered.get(p);
			send(new String(pa.send(p, pa.getToSend(), null, null)));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendPacket(String p, Packet pa) {

		try {
			send(p);

			send(new String(pa.send(p, pa.getToSend(), null, null)));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void log(String s) {
		Kronos.debug.getLogger().debug(s);
	}

	public void appendDataSilent(String msg, String dat, String side) {
		datalog.add("Recieved Data: " + dat + " On Side: " + side + " Extra Info: " + msg);
	}

	public void appendData(String msg, String dat, String side) {
		String s = "Recieved Data: " + dat + " On Side: " + side + " Extra Info: " + msg;
		datalog.add(s);
		log(s);
	}
}
