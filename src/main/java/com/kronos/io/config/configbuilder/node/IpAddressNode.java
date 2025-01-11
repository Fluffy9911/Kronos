package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class IpAddressNode extends Block {
	String ipAddress;

	public IpAddressNode(String name, String ipAddress) {
		super(false, name, new String[] { ipAddress });
		this.ipAddress = ipAddress;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// Extracting IP address value
		int ipStartIndex = val.indexOf("\"") + 1;
		int ipEndIndex = val.lastIndexOf("\"");
		this.ipAddress = val.substring(ipStartIndex, ipEndIndex);
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		return pref + getName() + "=\"" + ipAddress + "\";type=ip_address;";
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
