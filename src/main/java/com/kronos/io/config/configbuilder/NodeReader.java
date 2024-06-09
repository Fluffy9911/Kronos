/**
 * 
 */
package com.kronos.io.config.configbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class NodeReader {
	public static List<String> readBlocks(String line) {
		ArrayList<String> li = new ArrayList<String>();
		String[] s = line.split(";");

		for (int i = 0; i < s.length; i++) {
			li.add(s[i] + ";");
		}
		return li;
	}
}
