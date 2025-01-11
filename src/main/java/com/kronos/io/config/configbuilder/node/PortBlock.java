package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class PortBlock extends Block {
	int portNumber;

	public PortBlock(String name, int portNumber) {
		super(false, name, new String[] { Integer.toString(portNumber) });
		this.portNumber = portNumber;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting port number value
		int portStartIndex = val.indexOf("\"") + 1;
		int portEndIndex = val.lastIndexOf("\"");
		String portStr = val.substring(portStartIndex, portEndIndex);
		this.portNumber = Integer.parseInt(portStr);
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + portNumber + "\";type=port;";
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
}
