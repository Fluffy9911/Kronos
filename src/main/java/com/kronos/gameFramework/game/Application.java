/**
 * 
 */
package com.kronos.gameFramework.game;

import com.kronos.io.Config;

/**
 * a simple class that lets you run a simple NON-KRONOS application and headless
 */
public abstract class Application {
	private Config config;

	public Application() {
		super();
		config = new Config();
	}

	public abstract String programLocation();

	public Config getConfig() {
		return config;
	}

	public abstract void init();

	public abstract boolean close();

	public abstract void update();

	public abstract void onEnd();

	public void start() {
		init();

		while (!close()) {
			update();
		}
		onEnd();

	}

}
