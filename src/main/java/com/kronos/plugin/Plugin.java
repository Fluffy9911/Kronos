/**
 * 
 */
package com.kronos.plugin;

import com.kronos.graphixs.resources.Resource;

/**
 * 
 */
public interface Plugin {

	public void init();

	public void end();

	public Resource getPluginResource();

	public String pluginInfo();

}
