/**
 * 
 */
package com.kronos.graphixs.color;

import java.util.HashMap;

/**
 * 
 */
public class ColorConfig {
	HashMap<String, Color> colors;

	public ColorConfig() {
		super();
		this.colors = new HashMap<>();
	}

	public Color read(String id) {
		return colors.get(id);
	}

	public void write(String col, Color c) {
		colors.put(col, c);
	}

	public Color readOrDefault(String col, Color def) {
		if (read(col) == null) {
			write(col, def);
			return read(col);
		}
		return read(col);
	}

}
