/**
 * 
 */
package com.kronos.io.config.configbuilder;

import java.util.List;

/**
 * 
 */
public abstract class Node {

	public abstract String to(String pref, String app);

	public abstract void from(List<String> line, String pref, String app);

}
