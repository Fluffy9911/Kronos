/**
 * 
 */
package com.kronos.io.assets;

/**
 * 
 */
public class ResourceLocation {
	String path = "";
	String name = "";

	public ResourceLocation(String path) {
		super();
		this.path = path;
	}

	public ResourceLocation(String path, String name) {
		super();
		this.path = path + "/" + name;

	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

}
