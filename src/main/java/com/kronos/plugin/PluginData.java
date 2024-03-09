/**
 * 
 */
package com.kronos.plugin;

/**
 * 
 */
public class PluginData {

	String name;
	Plugin plugin;

	public PluginData(String name, Plugin plugin) {
		this.name = name;
		this.plugin = plugin;
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
		this.plugin = plugin;
	}

}
