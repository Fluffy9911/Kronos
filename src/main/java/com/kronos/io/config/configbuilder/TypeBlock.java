/**
 * 
 */
package com.kronos.io.config.configbuilder;

/**
 * 
 */
public class TypeBlock extends Block {
	String type;

	public TypeBlock(String type) {
		this.type = type;
	}

	@Override
	public void readIn(String val, String pref, String app) {
		type = val.substring(val.indexOf("=") + 1, val.lastIndexOf(";"));

	}

	@Override
	public String blockOut(String pref, String app, int l) {
		if (l == 0)
			return pref + "type=" + type + ";";
		else
			return "type=" + type + ";";
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

}
