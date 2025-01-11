/**
 * 
 */
package com.kronos.plugin;

import com.kronos.graphixs.resources.Resource;
import com.kronos.plugin.info.AuthorInfo;

/**
 * 
 */
public interface Plugin {

	public void init();

	public void end();

	public Resource getPluginResource();

	public String pluginInfo();

	public AuthorInfo getAuthorInfo();

}
