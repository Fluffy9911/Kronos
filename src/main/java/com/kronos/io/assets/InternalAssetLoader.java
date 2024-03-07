package com.kronos.io.assets;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.kronos.graphixs.texture.Texture;

public class InternalAssetLoader {
	Logger l;
	HashMap<String, String> load_que;
	HashMap<String, Texture> loaded;
	String current;

	// flags
	boolean exitonerror = false, reloadable = true;

	public InternalAssetLoader(String current) {
		super();
		this.current = current;

		load_que = new HashMap<>();
		loaded = new HashMap<>();

	}

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

	public boolean isExitonerror() {
		return exitonerror;
	}

	public void setExitonerror(boolean exitonerror) {
		this.exitonerror = exitonerror;
	}

	public boolean isReloadable() {
		return reloadable;
	}

	public void setReloadable(boolean reloadable) {
		this.reloadable = reloadable;
	}

	public void loadIn() {
		for (Map.Entry<String, String> entry : load_que.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			l.debug("Searching for id: {}", key);
			if (doesExist(val)) {

			}
		}
	}

	private File asFile(String val) {
		// TODO Auto-generated method stub
		return new File(val);
	}

	/**
	 * @return the loaded
	 */
	public HashMap<String, Texture> getLoaded() {
		return loaded;
	}

	/**
	 * @param loaded the loaded to set
	 */
	public void setLoaded(HashMap<String, Texture> loaded) {
		this.loaded = loaded;
	}

	/**
	 * @return the current
	 */
	public String getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(String current) {
		this.current = current;
	}

}
