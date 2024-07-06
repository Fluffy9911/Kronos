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

/**
 * 
 */
public class ResourceField<T> extends ResourceKey {
	T data;

	/**
	 * @param name
	 * @param path
	 */
	public ResourceField(String name, String path, T val) {
		super(name, path);
		this.data = val;
	}

	/**
	 * @param child
	 * @param object
	 */
	public ResourceField(ResourceKey child, T v) {
		super(child.name, child.path);
		this.data = v;
	}

	public T getValue() {
		if (data == null) {
			throw new NullPointerException("data was not created or read, did you call get()?");
		}

		return data;
	}

	@Override
	public ResourceField<T> get() {

		super.get();
		try {
			String data = Files.readString(this.asFile().toPath());
			if (!(data == "")) {
				Gson g = new Gson();
				return g.fromJson(data, ResourceField.class);
			} else {

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
