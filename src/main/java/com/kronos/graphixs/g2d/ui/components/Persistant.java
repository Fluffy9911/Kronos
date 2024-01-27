package com.kronos.graphixs.g2d.ui.components;

import com.kronos.graphixs.g2d.ui.ComponentHandler;
import com.kronos.io.config.ConfigFile;

public interface Persistant {

	public void load(ConfigFile file);

	public void write(ConfigFile file);

	public String id();

	public default void register(Persistant p, ComponentHandler ch) {
		ch.register(id(), p);

	}

}
