package com.kronos.io;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kronos.Kronos;

public class Config {

	HashMap<String, String> data;

	public Config() {
		super();
		data = new HashMap<String, String>();
	}

	public void appendString(String key, String value) {
		data.put(key, value);
	}

	public String readString(String key) {
		return data.get(key);
	}

	public void appendInt(String key, int i) {
		data.put(key, Integer.toString(i));
	}

	public int readInt(String key) {
		return Integer.parseInt(readString(key));
	}

	public void appendFloat(String key, float i) {
		data.put(key, Float.toString(i));
	}

	public Float readFloat(String key) {
		return Float.parseFloat(readString(key));
	}

	public void appendLong(String key, long i) {
		data.put(key, Long.toString(i));
	}

	public Long readLong(String key) {
		return Long.parseLong(readString(key));
	}

	public void appendShort(String key, short i) {
		data.put(key, Short.toString(i));
	}

	public Short readShort(String key) {
		return Short.parseShort(readString(key));
	}

	public void appendByte(String key, byte i) {
		data.put(key, Byte.toString(i));
	}

	public Byte readByte(String key) {
		return Byte.parseByte(readString(key));
	}

	public void appendBoolean(String key, boolean i) {
		data.put(key, Boolean.toString(i));
	}

	public Boolean readBoolean(String key) {
		return Boolean.parseBoolean(readString(key));
	}

	public void appendConfig(String key, Config value) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Config readConfig(String key) {
		Gson g = new Gson();
		Config v = g.fromJson(readString(key), Config.class);
		return v;

	}

	public void appendConfigArray(String key, Config[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Config[] readConfigArray(String key) {
		Gson g = new Gson();
		Config[] v = g.fromJson(readString(key), Config[].class);
		return v;

	}

	public void appendStringArray(String key, String[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public String[] readStringArray(String key) {
		Gson g = new Gson();
		String[] v = g.fromJson(readString(key), String[].class);
		return v;

	}

	public void appendIntegerArray(String key, Integer[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Integer[] readIntegerArray(String key) {
		Gson g = new Gson();
		Integer[] v = g.fromJson(readString(key), Integer[].class);
		return v;

	}

	public void appendFloatArray(String key, Float[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Float[] readFloatArray(String key) {
		Gson g = new Gson();
		Float[] v = g.fromJson(readString(key), Float[].class);
		return v;

	}

	public void appendLongArray(String key, Long[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Long[] readLongArray(String key) {
		Gson g = new Gson();
		Long[] v = g.fromJson(readString(key), Long[].class);
		return v;

	}

	public void appendBooleanArray(String key, Boolean[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Boolean[] readBooleanArray(String key) {
		Gson g = new Gson();
		Boolean[] v = g.fromJson(readString(key), Boolean[].class);
		return v;

	}

	public void appendByteArray(String key, Byte[] value) {
		Gson g = new Gson();
		String v = g.toJson(value);
		appendString(key, v);
	}

	public Byte[] readByteArray(String key) {
		Gson g = new Gson();
		Byte[] v = g.fromJson(readString(key), Byte[].class);
		return v;

	}

	public String writeOut() {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		return g.toJson(this);
	}

	public void appendStringList(String key, List<String> values) {
		Gson g = new Gson();
		String v = g.toJson(values);
		appendString(key, v);
	}

	public List<String> readStringList(String key) {
		Gson g = new Gson();
		String[] v = g.fromJson(readString(key), String[].class);
		if (v != null) {
			return List.of(v);
		}
		return new ArrayList<>();
	}

	public boolean containsStringValue(String key, String value) {
		List<String> stringList = readStringList(key);
		return stringList.contains(value);
	}

	public void appendIntegerList(String key, List<Integer> values) {
		Gson g = new Gson();
		String v = g.toJson(values);
		appendString(key, v);
	}

	public List<Integer> readIntegerList(String key) {
		Gson g = new Gson();
		Integer[] v = g.fromJson(readString(key), Integer[].class);
		if (v != null) {
			return List.of(v);
		}
		return new ArrayList<>();
	}

	public boolean containsIntegerValue(String key, int value) {
		List<Integer> integerList = readIntegerList(key);
		return integerList.contains(value);
	}

	public <T extends Number> void appendNumberMap(String key, Map<T, T> data) {
		Gson g = new Gson();
		String v = g.toJson(data);
		appendString(key, v);
	}

	public <T extends Number> Map<T, T> readNumberMap(String key) {
		Gson g = new Gson();
		String v = readString(key);
		Type mapType = new TypeToken<Map<T, T>>() {
		}.getType();
		return g.fromJson(v, mapType);

	}

	public boolean readOrWriteBoolean(String key, boolean def) {
		if (data.containsKey(key)) {
			return readBoolean(key);
		} else {
			appendBoolean(key, def);
			return def;
		}
	}

	public String readOrWriteString(String key, String def) {
		if (data.containsKey(key)) {
			return readString(key);
		} else {
			appendString(key, def);
			return def;
		}
	}

	public int readOrWriteInt(String key, int def) {
		if (data.containsKey(key)) {
			return readInt(key);
		} else {
			appendInt(key, def);
			return def;
		}
	}

	public void writeObject(String key, Object s) {
		Gson g = new Gson();
		String v = g.toJson(s);

		Kronos.debug.getLogger().fatal("Wrote Object {} to key {}", v, key);
		appendString(key, v);
	}

	public <T extends Object> T readObject(String key) {
		Gson g = new Gson();
		String v = readString(key);

		Type t = new TypeToken<T>() {
		}.getType();
		return g.fromJson(v, t);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T readOrWriteObject(String key, Object def) {
		if (data.containsKey(key)) {
			return readObject(key);
		} else {
			writeObject(key, def);
			return (T) def;
		}
	}

	public Config readOrWriteConfig(String key, Config val) {
		if (data.containsKey(key)) {
			return readConfig(key);
		} else {
			appendConfig(key, val);
			return val;
		}
	}

}
