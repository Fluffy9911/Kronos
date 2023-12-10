package com.kronos.io.config;

import com.kronos.io.Config;

public abstract class Configurement {

	Config config;
	
	public abstract void write(Config c);
	
	public abstract void read(Config c);
	
	public boolean hasBool(String key) {
		if(config.readBoolean(key)!=null) {
			return true;
		}
		return false;
	}
	public boolean readBool(String key) {
		if(config.readBoolean(key)!=null) {
			return config.readBoolean(key);
		}
		return false;
	}
	
	public void update(Config c) {
		config = c;
		read(c);
	}
	
}
