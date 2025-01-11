package com.kronos.io.file;

import java.util.LinkedHashMap;

import com.kronos.io.config.ConfigKey;

public class EasyConfig extends AdvancedConfig<ConfigKey> {
	/**
	 * @param filePath
	 */
	public EasyConfig(String filePath) {
		super(filePath);
		configMap = new LinkedHashMap<String, ConfigKey>();
	}

	@Override
	public void appendIntAdv(String name, String description, int value, int min, int max) {
		appendComment("An Integer Type: " + name + ", " + description + " Minimum Value: " + min + " Maximum Value: "
				+ max + ";");
		appendInt(name, value, min, max);
	}

	@Override
	public void appendFloatAdv(String name, String description, float value, float min, float max) {
		appendComment("An Floating Point Type: " + name + ", " + description + " Minimum Value: " + min
				+ " Maximum Value: " + max + ";");
		appendFloat(name, value, min, max);
	}

	@Override
	public void appendString(String name, String value) {
		configMap.put(name, new ConfigKey(name, "string", new String[] { value }, "", "", true, false));
	}

	@Override
	public void appendStringArray(String name, String[] value) {
		configMap.put(name, new ConfigKey(name, "string_arr", value, "", "", true, false));
	}

	@Override
	public void appendComment(String c) {
		configMap.put(c, new ConfigKey(c, "string", new String[] { c }, "", "", false, true));
	}

	@Override
	public void appendSection(String c) {
		configMap.put(c, new ConfigKey(c, "string", new String[] { c }, "", "", false, false, false, true));
		configMap.put(c, new ConfigKey(c, "string", new String[] { c }, "", "", false, true, true));
	}

	@Override
	public void appendInt(String name, int i, int min, int max) {
		configMap.put(name, new ConfigKey(name, "int", new String[] { i + "" }, min + "", max + "", false, false));
	}

	@Override
	public void appendIntArray(String name, int[] value, int min, int max) {

		String[] s = new String[value.length];

		for (int i = 0; i < value.length; i++) {
			s[i] = value[i] + "";
		}

		configMap.put(name, new ConfigKey(name, "int_arr", s, min + "", max + "", false, false));
	}

	@Override
	public void appendFloat(String name, float i, float min, float max) {
		configMap.put(name, new ConfigKey(name, "float", new String[] { i + "" }, min + "", max + "", false, false));
	}

	@Override
	public void appendFloatArray(String name, float[] value, float min, float max) {

		String[] s = new String[value.length];

		for (int i = 0; i < value.length; i++) {
			s[i] = value[i] + "";
		}

		configMap.put(name, new ConfigKey(name, "float_arr", s, min + "", max + "", false, false));
	}

	@Override
	public ConfigKey createEmptyKey() {
		// TODO Auto-generated method stub
		return new ConfigKey();
	}
}
