/**
 * 
 */
package com.kronos.core.res;

import java.io.File;
import java.io.IOException;

/**
 * 
 */
public class ResourceKey {
	public static String key_extension = ".rkey";
	String name, path;

	public static final ResourceKey kronos_base = new ResourceKey("KRONOS_BASE", "kronos/res");

	public ResourceKey(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String asPath() {
		return path + "/" + name;
	}

	public File asFile() {
		return new File(asPath() + key_extension);
	}

	public ResourceKey get() {

		File f = asFile();
		if (!f.exists()) {
			try {
				new File(path).mkdirs();
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this;
	}

	/**
	 * creates a new {@link ResourceKey} in the same path with a new name
	 * 
	 * @param name
	 * @return
	 */
	public ResourceKey child(String name) {
		return new ResourceKey(name, path);
	}

	/**
	 * creates a new {@link ResourceKey} in a sub path with a specified name
	 * 
	 * @param name
	 * @param sub
	 * @return
	 */
	public ResourceKey childPath(String name, String sub) {
		return new ResourceKey(name, path + "/" + sub);
	}

}
