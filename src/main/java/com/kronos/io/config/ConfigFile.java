package com.kronos.io.config;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kronos.Kronos;
import com.kronos.io.Config;
import com.kronos.io.FileLoader;
import com.kronos.io.ResourceIdentifier;

public class ConfigFile {
	String name, path;
	ResourceIdentifier id;
	FileLoader fl = Kronos.loader;
	public Config config;

	public ConfigFile(String name, String path, ResourceIdentifier id) {
		this.name = name;
		this.id = id;
		this.path = path;
		config = new Config();
	}

	public void load() {
		fl.createAt(path, name + ".json", id);
		try {
			config = fl.tryRead(name + ".json", path, id);
		} catch (Exception e) {
			Kronos.debug.getLogger().debug(
					"File Loading caught a soft error, sending error in-case this is not a false alarm. Attempting fix",
					e);
			config = new Config();
		}
		if (config == null) {
			config = new Config();
		}
	}

	public void write() {
		File dir = new File(id.getBasePath() + "/" + path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File f = new File(id.getBasePath() + "/" + path + "/" + name + ".json");
		f.delete();
		fl.createAndWrite(id, path + "/" + name + ".json", config.writeOut());
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendString(java.lang.String, java.lang.String)
	 */
	public void appendString(String key, String value) {
		config.appendString(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readString(java.lang.String)
	 */
	public String readString(String key) {
		return config.readString(key);
	}

	/**
	 * @param key
	 * @param i
	 * @see com.kronos.io.Config#appendInt(java.lang.String, int)
	 */
	public void appendInt(String key, int i) {
		config.appendInt(key, i);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readInt(java.lang.String)
	 */
	public int readInt(String key) {
		return config.readInt(key);
	}

	/**
	 * @param key
	 * @param i
	 * @see com.kronos.io.Config#appendFloat(java.lang.String, float)
	 */
	public void appendFloat(String key, float i) {
		config.appendFloat(key, i);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readFloat(java.lang.String)
	 */
	public Float readFloat(String key) {
		return config.readFloat(key);
	}

	/**
	 * @param key
	 * @param i
	 * @see com.kronos.io.Config#appendLong(java.lang.String, long)
	 */
	public void appendLong(String key, long i) {
		config.appendLong(key, i);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readLong(java.lang.String)
	 */
	public Long readLong(String key) {
		return config.readLong(key);
	}

	/**
	 * @param key
	 * @param i
	 * @see com.kronos.io.Config#appendShort(java.lang.String, short)
	 */
	public void appendShort(String key, short i) {
		config.appendShort(key, i);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readShort(java.lang.String)
	 */
	public Short readShort(String key) {
		return config.readShort(key);
	}

	/**
	 * @param key
	 * @param i
	 * @see com.kronos.io.Config#appendByte(java.lang.String, byte)
	 */
	public void appendByte(String key, byte i) {
		config.appendByte(key, i);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readByte(java.lang.String)
	 */
	public Byte readByte(String key) {
		return config.readByte(key);
	}

	/**
	 * @param key
	 * @param i
	 * @see com.kronos.io.Config#appendBoolean(java.lang.String, boolean)
	 */
	public void appendBoolean(String key, boolean i) {
		config.appendBoolean(key, i);
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return config.hashCode();
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readBoolean(java.lang.String)
	 */
	public Boolean readBoolean(String key) {
		return config.readBoolean(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendConfig(java.lang.String,
	 *      com.kronos.io.Config)
	 */
	public void appendConfig(String key, Config value) {
		config.appendConfig(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readConfig(java.lang.String)
	 */
	public Config readConfig(String key) {
		return config.readConfig(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendConfigArray(java.lang.String,
	 *      com.kronos.io.Config[])
	 */
	public void appendConfigArray(String key, Config[] value) {
		config.appendConfigArray(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readConfigArray(java.lang.String)
	 */
	public Config[] readConfigArray(String key) {
		return config.readConfigArray(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendStringArray(java.lang.String,
	 *      java.lang.String[])
	 */
	public void appendStringArray(String key, String[] value) {
		config.appendStringArray(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readStringArray(java.lang.String)
	 */
	public String[] readStringArray(String key) {
		return config.readStringArray(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendIntegerArray(java.lang.String,
	 *      java.lang.Integer[])
	 */
	public void appendIntegerArray(String key, Integer[] value) {
		config.appendIntegerArray(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readIntegerArray(java.lang.String)
	 */
	public Integer[] readIntegerArray(String key) {
		return config.readIntegerArray(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendFloatArray(java.lang.String,
	 *      java.lang.Float[])
	 */
	public void appendFloatArray(String key, Float[] value) {
		config.appendFloatArray(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readFloatArray(java.lang.String)
	 */
	public Float[] readFloatArray(String key) {
		return config.readFloatArray(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendLongArray(java.lang.String, java.lang.Long[])
	 */
	public void appendLongArray(String key, Long[] value) {
		config.appendLongArray(key, value);
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return config.equals(obj);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readLongArray(java.lang.String)
	 */
	public Long[] readLongArray(String key) {
		return config.readLongArray(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendBooleanArray(java.lang.String,
	 *      java.lang.Boolean[])
	 */
	public void appendBooleanArray(String key, Boolean[] value) {
		config.appendBooleanArray(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readBooleanArray(java.lang.String)
	 */
	public Boolean[] readBooleanArray(String key) {
		return config.readBooleanArray(key);
	}

	/**
	 * @param key
	 * @param value
	 * @see com.kronos.io.Config#appendByteArray(java.lang.String, java.lang.Byte[])
	 */
	public void appendByteArray(String key, Byte[] value) {
		config.appendByteArray(key, value);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readByteArray(java.lang.String)
	 */
	public Byte[] readByteArray(String key) {
		return config.readByteArray(key);
	}

	/**
	 * @return
	 * @see com.kronos.io.Config#writeOut()
	 */
	public String writeOut() {
		return config.writeOut();
	}

	/**
	 * @param key
	 * @param values
	 * @see com.kronos.io.Config#appendStringList(java.lang.String, java.util.List)
	 */
	public void appendStringList(String key, List<String> values) {
		config.appendStringList(key, values);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readStringList(java.lang.String)
	 */
	public List<String> readStringList(String key) {
		return config.readStringList(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see com.kronos.io.Config#containsStringValue(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean containsStringValue(String key, String value) {
		return config.containsStringValue(key, value);
	}

	/**
	 * @param key
	 * @param values
	 * @see com.kronos.io.Config#appendIntegerList(java.lang.String, java.util.List)
	 */
	public void appendIntegerList(String key, List<Integer> values) {
		config.appendIntegerList(key, values);
	}

	/**
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readIntegerList(java.lang.String)
	 */
	public List<Integer> readIntegerList(String key) {
		return config.readIntegerList(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see com.kronos.io.Config#containsIntegerValue(java.lang.String, int)
	 */
	public boolean containsIntegerValue(String key, int value) {
		return config.containsIntegerValue(key, value);
	}

	/**
	 * @param <T>
	 * @param key
	 * @param data
	 * @see com.kronos.io.Config#appendNumberMap(java.lang.String, java.util.Map)
	 */
	public <T extends Number> void appendNumberMap(String key, Map<T, T> data) {
		config.appendNumberMap(key, data);
	}

	/**
	 * @param <T>
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readNumberMap(java.lang.String)
	 */
	public <T extends Number> Map<T, T> readNumberMap(String key) {
		return config.readNumberMap(key);
	}

	/**
	 * @param key
	 * @param def
	 * @return
	 * @see com.kronos.io.Config#readOrWriteBoolean(java.lang.String, boolean)
	 */
	public boolean readOrWriteBoolean(String key, boolean def) {
		return config.readOrWriteBoolean(key, def);
	}

	/**
	 * @param key
	 * @param def
	 * @return
	 * @see com.kronos.io.Config#readOrWriteString(java.lang.String,
	 *      java.lang.String)
	 */
	public String readOrWriteString(String key, String def) {
		return config.readOrWriteString(key, def);
	}

	/**
	 * @param key
	 * @param def
	 * @return
	 * @see com.kronos.io.Config#readOrWriteInt(java.lang.String, int)
	 */
	public int readOrWriteInt(String key, int def) {
		return config.readOrWriteInt(key, def);
	}

	/**
	 * @param key
	 * @param s
	 * @see com.kronos.io.Config#writeObject(java.lang.String, java.lang.Object)
	 */
	public void writeObject(String key, Object s) {
		config.writeObject(key, s);
	}

	/**
	 * @param <T>
	 * @param key
	 * @return
	 * @see com.kronos.io.Config#readObject(java.lang.String)
	 */
	public <T> T readObject(String key) {
		return config.readObject(key);
	}

	/**
	 * @param <T>
	 * @param key
	 * @param def
	 * @return
	 * @see com.kronos.io.Config#readOrWriteObject(java.lang.String,
	 *      java.lang.Object)
	 */
	public <T> T readOrWriteObject(String key, Object def) {
		return config.readOrWriteObject(key, def);
	}

	/**
	 * @param key
	 * @param val
	 * @return
	 * @see com.kronos.io.Config#readOrWriteConfig(java.lang.String,
	 *      com.kronos.io.Config)
	 */
	public Config readOrWriteConfig(String key, Config val) {
		return config.readOrWriteConfig(key, val);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return config.toString();
	}

	public float readOrWriteFloat(String string, float num) {
		// TODO Auto-generated method stub
		return config.readOrWriteFloat(string, num);
	}

}
