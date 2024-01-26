package com.kronos.io.assets;

import java.io.File;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;

import com.kronos.graphixs.display.Texture;

public class InternalAssetLoader {
	Logger l;
	HashMap<String, String> load_que;
	HashMap<String, Texture> loaded;
	String current;

	// flags
	boolean exitonerror = false, reloadable = true;

	/**
	 * Equals current + / + sub + name + .png
	 * 
	 * @param name
	 * @param sub
	 * @return
	 */
	public String getDestination(String name, String sub) {
		return current + "/" + sub + "/" + name + ".png";
	}

	public boolean doesExist(String loc) {
		return new File(loc).exists();
	}

}
