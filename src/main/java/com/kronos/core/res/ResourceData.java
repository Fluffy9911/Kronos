/**
 * 
 */
package com.kronos.core.res;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kronos.io.Config;

/**
 * 
 */
public class ResourceData extends ResourceKey {
	Config config;

	public static ResourceData fromKey(ResourceKey rk) {
		return new ResourceData(rk.getName(), rk.getPath());
	}

	/**
	 * @param name
	 * @param path
	 */
	public ResourceData(String name, String path) {
		super(name, path);

	}

	public Config getConfig() {
		if (config == null) {
			throw new NullPointerException("Config was not created or read, did you call get()?");
		}

		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	@Override
	public ResourceKey get() {

		super.get();
		try {
			String data = Files.readString(this.asFile().toPath());
			if (!(data == "")) {
				Gson g = new Gson();
				return g.fromJson(data, ResourceData.class);
			} else {
				config = new Config();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	public void free() {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		try {
			Files.writeString(this.asFile().toPath(), g.toJson(this), StandardOpenOption.CREATE_NEW);
		} catch (FileAlreadyExistsException e) {
			this.asFile().delete();
			free();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
