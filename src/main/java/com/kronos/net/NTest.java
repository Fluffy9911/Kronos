/**
 * 
 */
package com.kronos.net;

import java.net.InetSocketAddress;

import com.kronos.net.connection.Connector;

/**
 * 
 */
public class NTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connector cn = new Connector(new InetSocketAddress("192.168.219.9", 0));
		cn.open(true);
	}

}
