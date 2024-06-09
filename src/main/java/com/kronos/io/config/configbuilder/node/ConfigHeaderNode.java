package com.kronos.io.config.configbuilder.node;

import com.kronos.io.config.configbuilder.Block;

public class ConfigHeaderNode extends Block {
	String version;
	String configName;

	public ConfigHeaderNode(String version, String configName) {
		this.version = version;
		this.configName = configName;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		// ConfigHeaderNode doesn't need to be read in
	}

	@Override
	public String blockOut(String pref, String app, int l) {
		StringBuilder builder = new StringBuilder();
		builder.append(pref).append("##[Version=").append(version).append(app);
		builder.append(pref).append("ConfigName=").append(configName).append(app).append("]##");
		return builder.toString();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}
}
