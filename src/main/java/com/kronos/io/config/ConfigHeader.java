/**
 * 
 */
package com.kronos.io.config;

/**
 * 
 */
public class ConfigHeader implements IConfigKey {

	String name;
	int version;

	public ConfigHeader(String name, int version) {
		this.name = name;
		this.version = version;
	}

	@Override
	public String to() {
		return "#[config_type=" + name + ",version=" + version + "]";
	}

	@Override
	public IConfigKey from(String s) {
		// Extracting name and version from the export syntax
		String[] parts = s.split("[=,]");

		// Assuming the format is consistent with config_type=name,version=value
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].trim().equals("config_type")) {
				name = parts[i + 1].trim();
			} else if (parts[i].trim().equals("version")) {
				version = Integer.parseInt(parts[i + 1].trim());
			}
		}

		return this;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

}
