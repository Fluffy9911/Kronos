/**
 * 
 */
package com.kronos.net;

import java.io.IOException;
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
		try {
			cn.c.listenTerminal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cn.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
