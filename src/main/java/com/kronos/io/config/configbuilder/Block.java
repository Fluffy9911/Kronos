/**
 * 
 */
package com.kronos.io.config.configbuilder;

/**
 * 
 */
public abstract class Block {

	boolean isarr = false;

	String name;
	String[] data;

	public Block() {
	}

	public Block(boolean isarr) {
		this.isarr = isarr;
	}

	public Block(boolean isarr, String name, String[] data) {
		this.isarr = isarr;
		this.name = name;
		this.data = data;
	}

	/**
	 * @return the isarr
	 */
	public boolean isarr() {
		return isarr;
	}

	public abstract void readIn(String val, String pref, String app);

	public abstract String blockOut(String pref, String app, int l);

	/**
	 * @return the isarr
	 */
	public boolean isIsarr() {
		return isarr;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the data
	 */
	public String[] getData() {
		return data;
	}

}
