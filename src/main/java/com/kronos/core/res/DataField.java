/**
 * 
 */
package com.kronos.core.res;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;

/**
 * 
 */
public class DataField<T> extends ResourceKey {
	T obj;

	ResourceKey rk;

	public static <T> T serializableField(String name, T defval) {
		return new DataField<T>(defval, kronos_base.child(name)).get().free().getObj();
	}

	public DataField(T obj, ResourceKey rk) {
		super(rk.name, rk.path);
		this.obj = obj;
		this.rk = rk;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataField<T> get() {
		super.get();
		String d = "";
		try {

			d = Files.readString(this.asFile().toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!(d == "")) {
			GsonBuilder gb = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER);
			this.obj = (T) gb.create().fromJson(d, obj.getClass());
		} else {
			System.err.println("Input String is null");
		}
		return this;

	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public DataField<T> free() {
		String d = new GsonBuilder().setPrettyPrinting().setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER)
				.create().toJson(obj);
		try {
			Files.writeString(this.asFile().toPath(), d, StandardOpenOption.CREATE_NEW);
		} catch (FileAlreadyExistsException e) {
			this.asFile().delete();
			free();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public static Number parse(String str) {
		Number number = null;
		try {
			number = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			try {
				number = Double.parseDouble(str);
			} catch (NumberFormatException e1) {
				try {
					number = Integer.parseInt(str);
				} catch (NumberFormatException e2) {
					try {
						number = Long.parseLong(str);
					} catch (NumberFormatException e3) {
						throw e3;
					}
				}
			}
		}
		return number;
	}
}
