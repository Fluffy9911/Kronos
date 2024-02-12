/**
 * 
 */
package com.kronos.net.data.packet;

import com.google.gson.Gson;
import com.kronos.io.Config;
import com.kronos.net.data.Packet;

/**
 * 
 */
public class ConfigPacket extends Packet {
	Config cfg;

	public ConfigPacket() {
		super();
	}

	public ConfigPacket(Config cfg) {
		super();
		this.cfg = cfg;
	}

	@Override
	public void dealWithInputData(String dat) {
		Gson g = new Gson();
		System.out.println(dat);
		cfg = g.fromJson(dat, Config.class);
		System.out.println(cfg.toString());
	}

	@Override
	public String getToSend() {

		if (cfg != null) {
			Gson g = new Gson();
			return g.toJson(cfg);

		}
		return "null";
	}

	@Override
	public Object getCurrent() {
		// TODO Auto-generated method stub
		return cfg;
	}

	@Override
	public void initServerSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initClientSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sentClientSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveClientSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sentServerSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveServerSide() {
		// TODO Auto-generated method stub

	}

}
