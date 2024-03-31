/**
 * 
 */
package com.kronos.plugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kronos.Kronos;
import com.kronos.io.ResourceIdentifier;
import com.kronos.io.config.ConfigFile;

/**
 * 
 */
public class PluginData {

	String name;
	static Plugin plugin;

	String[] pluginargs;

	public PluginData(String name, Plugin plugin) {
		this.name = name;
		PluginData.plugin = plugin;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	/**
	 * @param plugin the plugin to set
	 */
	public void setPlugin(Plugin plugin) {
		PluginData.plugin = plugin;
	}

	public static Logger getKronosLogger() {
		return LogManager.getLogger();
	}

	public static ResourceIdentifier getIdentifierOut() {
		return Kronos.kronos_out;
	}

	public static ResourceIdentifier getIdentifierRid() {
		return Kronos.kronos_rid;
	}

	public String[] getPluginargs() {
		return new String[] { "-noargs" };
	}

	public static ConfigFile createConfig(String path, String name) {
		if (name.endsWith(".json")) {
			return new ConfigFile(path, name.replace(".json", ""), getIdentifierOut());
		} else {
			return new ConfigFile(path, name, getIdentifierOut());
		}
	}

}
