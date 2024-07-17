/**
 * 
 */
package com.kronos.io;

import com.kronos.Kronos;

/**
 * 
 */
public class KeyMapping {

	String[] mappings;

	public KeyMapping(String... mappings) {
		super();
		this.mappings = mappings;
	}

	public String[] getMappings() {
		return mappings;
	}

	public void setMappings(String[] mappings) {
		this.mappings = mappings;
	}

	public int[] toCodes() {
		int[] i = new int[mappings.length];
		for (int j = 0; j < mappings.length; j++) {
			if (InputHandler.key_mapping.get(mappings[j]) != null) {
				i[j] = InputHandler.key_mapping.get(mappings[j]).intValue();
			} else {
				Kronos.debug.getLogger().error("Key Mapping Doesnt Exist!: {}", mappings[j]);
			}
		}
		return i;
	}

}
