/**
 * 
 */
package com.kronos.core.res;

import java.io.File;

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

}
