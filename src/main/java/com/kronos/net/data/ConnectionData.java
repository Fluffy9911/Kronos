/**
 * 
 */
package com.kronos.net.data;

import com.kronos.net.data.packet.Side;

/**
 * 
 */
public class ConnectionData {
	long connectionStart = 0;

	long sentPackets = 0;

	Side side;

	int port;

	public ConnectionData(long connectionStart, Side side, int port) {
		super();
		this.connectionStart = connectionStart;
		this.sentPackets = 0;
		this.side = side;
		this.port = port;
	}

	public long getSentPackets() {
		return sentPackets;
	}

	public void setSentPackets(long sentPackets) {
		this.sentPackets = sentPackets;
	}

	public long getConnectionStart() {
		return connectionStart;
	}

	public Side getSide() {
		return side;
	}

	public int getPort() {
		return port;
	}

	public void setSide(Side side) {
		this.side = side;
	}

}
