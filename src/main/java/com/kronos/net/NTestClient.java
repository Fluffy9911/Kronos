/**
 * 
 */
package com.kronos.net;

import java.net.InetSocketAddress;

import com.kronos.net.connection.Connector;

/**
 * 
 */
public class NTestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connector cn = new Connector(new InetSocketAddress("192.168.219.20", 255), 255);
		cn.openClient();

	}

}
