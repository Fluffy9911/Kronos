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
import java.util.Map;
import java.util.concurrent.Executors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.core.util.BufferedStreamReader;
import com.kronos.net.data.ConnectionData;
import com.kronos.net.data.Packet;
import com.kronos.net.data.packet.ConfigPacket;
import com.kronos.net.data.packet.HandShake;
import com.kronos.net.data.packet.Side;
import com.kronos.net.data.packet.UnsecurePacket;

public class Connection {

	private ServerSocket local;
	private Socket connection;
	private LinkedList<String> databuffer, cbuf;
	private InetSocketAddress ss;
	private BufferedStreamReader bs, si;
	private HashMap<String, Packet> registered;
	private SecretKey key = null;
	private ConnectionData data;
	private boolean isServer = true, isConnected = false, isWaiting = true, handshake = false;
	private ArrayList<String> datalog;
	private String cp = "null";

	{
		datalog = new ArrayList<>();
	}

	public Connection(ServerSocket server) {
		this.local = server;
		this.isServer = true;
		this.databuffer = new LinkedList<>();
		this.cbuf = new LinkedList<>();
		this.registerPackets();
	}

	public Connection(InetSocketAddress s) {
		this.databuffer = new LinkedList<>();
		this.cbuf = new LinkedList<>();
		this.ss = s;
		this.isServer = false;
		this.registerPackets();
	}

	public void setDatabuffer(LinkedList<String> databuffer) {
		this.databuffer = databuffer;
	}

	public void setCbuf(LinkedList<String> cbuf) {
		this.cbuf = cbuf;
	}

	public void registerPackets() {
		registered = new HashMap<>();
		registered.put("unsecure", new UnsecurePacket());
		registered.put("handshake", new HandShake(this));
		registered.put("config", new ConfigPacket());
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
				handleReceivedData(s);
			}
		};
		bs.begin(Executors.newCachedThreadPool(), connection.getInputStream());
	}

	public void listenTerminal() throws IOException {
		si = new BufferedStreamReader() {
			@Override
			public void onRecieve(String s) {
				try {
					handleTerminalInput(s);
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
		out.flush();
	}

	public void sendPacket(String p) {
		try {
			send(p);
			Packet pa = registered.get(p);
			send(new String(pa.send(pa.getToSend(), key)));
			handleSentPacket(pa);
		} catch (Exception e) {
			handleException(e);
		}
	}

	public void sendPacket(String p, Packet pa) {
		try {
			send(p);
			send(new String(pa.send(pa.getToSend(), key)));
			handleSentPacket(pa);
		} catch (Exception e) {
			handleException(e);
		}
	}

	public String readNextInBuffer() {
		return databuffer.pop();
	}

	public void tryConnectServer(Logger l) {
		l.debug("Starting Server Connect");
		this.isWaiting = true;
		try {
			this.connection = local.accept();
			l.debug("Connected!");
			this.isWaiting = false;
			this.isConnected = true;
			this.data = new ConnectionData(System.currentTimeMillis(), Side.SERVER, this.local.getLocalPort());
			this.initServer();
			l.debug("Client: {}", connection.getInetAddress().getHostAddress());
		} catch (IOException e) {
			handleException(e);
		}
		l.debug("Connected to Client");
	}

	public void tryConnectClient(Logger l) {
		l.debug("Starting Client Connect {}", ss.getAddress().getHostName());
		this.isWaiting = true;
		try {
			this.connection = new Socket();
			this.connection.connect(ss);
			this.data = new ConnectionData(System.currentTimeMillis(), Side.CLIENT, ss.getPort());
			this.isWaiting = false;
			this.isConnected = true;
			l.debug("Connected to server");

			this.initClient();
		} catch (UnknownHostException e) {
			handleException(e);
		} catch (IOException e) {
			handleException(e);
		}
		l.debug("Client connected");
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
		Kronos.debug.getLogger().debug("A connection error has occurred: {}", s);
	}

	public void log(String s) {
		Kronos.debug.getLogger().debug(s);
	}

	public void appendDataSilent(String msg, String dat, String side) {
		String log = "Received Data: " + dat + " On Side: " + side + " Extra Info: " + msg;
		datalog.add(log);
	}

	public void appendData(String msg, String dat, String side) {
		String log = "Received Data: " + dat + " On Side: " + side + " Extra Info: " + msg;
		datalog.add(log);
		log(log);
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}

	private void handleReceivedData(String s) {
		if (!cp.equals("null") && !cp.equals(s)) {
			Packet p = registered.get(cp);
			try {
				System.out.println("packet received: " + cp);
				p.receive(s.getBytes(), key);

				if (this.getSide() == Side.SERVER) {
					p.recieveServerSide();
				}
				if (this.getSide() == Side.CLIENT) {
					p.recieveClientSide();
				}
				cp = "null";
			} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
					| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
				handleException(e);
			} catch (Exception e) {
				handleException(e);
			}
		}
		if (registered.containsKey(s)) {
			cp = s;
		} else {
			cp = "null";
		}

		appendData("input msg", s, "CONNECTION RECEIVED DATA");
		databuffer.add(s);
	}

	private void handleTerminalInput(String s) throws IOException {
		appendData("input terminal", s, "TERMINAL RECEIVED DATA");
		listenOnInput(s);
	}

	private void handleSentPacket(Packet pa) {
		if (this.getSide() == Side.SERVER) {
			pa.sentServerSide();
		}
		if (this.getSide() == Side.CLIENT) {
			pa.sentClientSide();
		}
	}

	private void handleException(Exception e) {
		e.printStackTrace();
	}

	private void initClient() {
		for (Map.Entry<String, Packet> entry : registered.entrySet()) {
			entry.getValue().initClientSide();
		}
	}

	private void initServer() {
		for (Map.Entry<String, Packet> entry : registered.entrySet()) {
			entry.getValue().initServerSide();
		}
	}

	public Side getSide() {
		return data.getSide();
	}
}
