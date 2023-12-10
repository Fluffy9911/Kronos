package com.kronos.graphixs.resources;

public interface Resource {

	public void load();

	/**
	 * Should be called to destroy an objects used memory
	 */
	public void close();

}
